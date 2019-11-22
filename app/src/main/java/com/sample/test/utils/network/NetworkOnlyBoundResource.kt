package com.sample.test.utils.network

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.sample.test.data.domain.model.GenericReqAndResp
import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.coroutines.coroutineContext

/**
 * Created by SangiliPandian C on 22-11-2019.
 */
abstract class NetworkOnlyBoundResource<ResultType> {

    private val TAG = NetworkBoundResource::class.java.name

    private val result = MediatorLiveData<Resource<ResultType>>()
    private val supervisorJob = SupervisorJob()

    suspend fun build(): NetworkOnlyBoundResource<ResultType> {
        withContext(Dispatchers.Main) {
            result.value =
                Resource.loading(null)
        }
        CoroutineScope(coroutineContext).launch(supervisorJob) {
            try {
                fetchFromNetwork()
            } catch (e: Exception) {
                Timber.d(TAG, "An error happened: $e")
                setValue(Resource.error(e.message, null))
            }
        }
        return this
    }

    private suspend fun fetchFromNetwork() {
        Timber.d(TAG, "Fetch data from network")
        val apiResponse = createCall()
        Timber.d(TAG, "Data fetched from network")
        val data = setData(apiResponse)
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                val responseData = apiResponse as GenericReqAndResp<*>
                if (responseData.data != null) {
                    result.addSource(data) { newData ->
                        setValue(Resource.success(newData))
                    }
                } else {
                    result.addSource(data) { newData ->
                        val errorMsg = responseData.message
                        setValue(Resource.error(errorMsg, newData))
                    }
                }
            }
        }
    }

    @MainThread
    private fun setData(response: ResultType): MutableLiveData<ResultType> {
        val data = MutableLiveData<ResultType>()
        GlobalScope.launch {
            withContext(Dispatchers.Main) { data.value = response }
        }
        return data
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        Timber.d(TAG, "Resource: $newValue")
        if (result.value != newValue) result.postValue(newValue)

    }

    @MainThread
    protected abstract suspend fun createCall(): ResultType
}

