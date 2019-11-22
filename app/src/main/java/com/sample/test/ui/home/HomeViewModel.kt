package com.sample.test.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sample.test.data.domain.usecase.HomeUseCase
import com.sample.test.db.entity.Home
import com.sample.test.ui.base.BaseViewModel
import com.sample.test.utils.network.AppDispatchers
import com.sample.test.utils.network.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by SangiliPandian C on 22-11-2019.
 */
class HomeViewModel(private val useCase: HomeUseCase, private val dispatchers: AppDispatchers) :
    BaseViewModel() {

    private var dataSource: LiveData<Resource<List<Home>>> = MutableLiveData()
    private val _data = MediatorLiveData<Resource<List<Home>>>()
    val data: LiveData<Resource<List<Home>>> get() = _data

    private var _dataSource: LiveData<Resource<List<String>>> = MutableLiveData()
    private val _dataValue = MediatorLiveData<Resource<List<String>>>()
    val response: LiveData<Resource<List<String>>> get() = _dataValue

    fun getData() = viewModelScope.launch(dispatchers.main) {
        _data.removeSource(dataSource)
        withContext(dispatchers.io) {
            dataSource = useCase.getData()
        }
        _data.addSource(dataSource) {
            _data.value = it
        }
    }

    fun getDataAsString() = viewModelScope.launch(dispatchers.main) {
        _dataValue.removeSource(_dataSource)
        withContext(dispatchers.io) {
            _dataSource = useCase.getDataAsString()
        }
        _dataValue.addSource(_dataSource) {
            _dataValue.value = it
        }
    }
}
