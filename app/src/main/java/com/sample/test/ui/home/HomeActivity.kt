package com.sample.test.ui.home

import android.os.Bundle
import androidx.lifecycle.Observer
import com.sample.test.R
import com.sample.test.db.entity.Home
import com.sample.test.di.injectDependencies
import com.sample.test.ui.base.BaseActivity
import com.sample.test.ui.home.adapter.HomeAdapter
import com.sample.test.utils.extension.toast
import com.sample.test.utils.network.Resource
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject

/**
 * Created by SangiliPandian C on 22-11-2019.
 */
class HomeActivity : BaseActivity() {

    private val vm: HomeViewModel by inject()
    private val adapter = HomeAdapter()

    override val layoutResourceId = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
        initUI()
        fetchData()
    }

    private fun fetchData() {
        vm.getData()
    }

    private fun initUI() {
        rvHome.adapter = adapter
        vm.data.observe(this, Observer { updateUI(it) })
    }

    private fun updateUI(response: Resource<List<Home>>?) {
        response?.let {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    hideLoading()
                    //adapter.submitList(response.data)
                }
                Resource.Status.LOADING -> showLoading()
                Resource.Status.ERROR -> {
                    hideLoading()
                    toast(it.error.toString())
                }
            }
        }
    }
}
