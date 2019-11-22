package com.sample.test.utils.network

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sample.test.data.domain.model.GenericReqAndResp
import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.coroutines.coroutineContext

/**
 * Created by SangiliPandian C on 22-11-2019.
 */
abstract class NetworkBoundResource<ResultType, RequestType> {

    private val TAG = NetworkBoundResource::class.java.name

    private val result = MutableLiveData<Resource<ResultType>>()
    private val supervisorJob = SupervisorJob()

    suspend fun build(): NetworkBoundResource<ResultType, RequestType> {
        withContext(Dispatchers.Main) {
            result.value = Resource.loading(null)
        }
        CoroutineScope(coroutineContext).launch(supervisorJob) {
            val dbResult = loadFromDb()
            if (shouldFetch(dbResult)) {
                try {
                    fetchFromNetwork(dbResult)
                } catch (e: Exception) {
                    Timber.d(TAG, "An error happened: $e")
                    setValue(Resource.error(e.message, loadFromDb()))
                }
            } else {
                Timber.d(TAG, "Return data from local database")
                setValue(Resource.success(dbResult))
            }
        }
        return this
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    // ---

    private suspend fun fetchFromNetwork(dbResult: ResultType) {
        Timber.d(TAG, "Fetch data from network")
        setValue(Resource.loading(dbResult)) // Dispatch latest value quickly (UX purpose)
        val apiResponse = createCall()
        Timber.e(TAG, "Data fetched from network")
        val processRes = processResponse(apiResponse)
        saveCallResults(processRes)
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                val responseData = apiResponse as GenericReqAndResp<*>
                if (responseData.data != null) {
                    setValue(Resource.success(loadFromDb()))
                } else {
                    val errorMsg = responseData.message
                    setValue(Resource.error(errorMsg, loadFromDb()))
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        Timber.d(TAG, "Resource: $newValue")
        if (result.value != newValue) result.postValue(newValue)
    }

    @WorkerThread
    protected abstract fun processResponse(response: RequestType): ResultType

    @WorkerThread
    protected abstract suspend fun saveCallResults(items: ResultType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract suspend fun loadFromDb(): ResultType

    @MainThread
    protected abstract suspend fun createCall(): RequestType
}

