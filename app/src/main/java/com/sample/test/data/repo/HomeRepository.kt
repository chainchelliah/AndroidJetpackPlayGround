package com.sample.test.data.repo

import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.sample.test.data.api.HomeApi
import com.sample.test.data.domain.model.GenericReqAndResp
import com.sample.test.db.dao.HomeDao
import com.sample.test.db.entity.Home
import com.sample.test.utils.decodeToString
import com.sample.test.utils.fromJson
import com.sample.test.utils.network.NetworkBoundResource
import com.sample.test.utils.network.Resource

/**
 * Created by SangiliPandian C on 22-11-2019.
 */

interface HomeRepository {
    suspend fun loadData(): LiveData<Resource<List<Home>>>
}

class HomeRepositoryImpl(private val api: HomeApi, private val dao: HomeDao) : HomeRepository {

    override suspend fun loadData(): LiveData<Resource<List<Home>>> {
        return object :
            NetworkBoundResource<List<Home>, GenericReqAndResp<List<String>>>() {

            override fun processResponse(response: GenericReqAndResp<List<String>>): List<Home> =
                Gson().fromJson<List<Home>>(decodeToString(response.data.first()))

            override suspend fun saveCallResults(items: List<Home>) = dao.saveAll(items)

            override fun shouldFetch(data: List<Home>?): Boolean = data.isNullOrEmpty()

            override suspend fun loadFromDb(): List<Home> = dao.getAllData()

            override suspend fun createCall(): GenericReqAndResp<List<String>> = api.fetchData()

        }.build().asLiveData()
    }

}
