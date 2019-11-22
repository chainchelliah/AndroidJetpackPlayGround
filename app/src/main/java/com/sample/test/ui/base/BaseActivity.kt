package com.sample.test.ui.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import com.sample.test.R
import com.sample.test.utils.Constants.CONNECTIVITY_CHANGE
import com.sample.test.utils.extension.gone
import com.sample.test.utils.extension.isNetworkAvailable
import com.sample.test.utils.extension.snack
import com.sample.test.utils.extension.visible
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.error_content.*

/**
 * Created by SangiliPandian C on 22-11-2019.
 */
abstract class BaseActivity : AppCompatActivity() {

    protected var currentView: View? = null
    private var lastViewId: Int? = null

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        init(layoutResourceId)
    }

    override fun onResume() {
        super.onResume()
        registerNetworkListener()
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(networkChangeReceiver)
    }

    private fun init(layoutId: Int) {
        if (vsContent != null) {
            vsContent.layoutResource = layoutId
            currentView = vsContent.inflate()
        }
        currentView?.let { loadView(it) }
    }

    private fun loadView(currentView: View) {
        lastViewId?.let { findViewById<View>(it)?.gone() }
        lastViewId = currentView.id
        currentView.visible()
    }

    fun hideLoading() = progressbar?.gone()

    fun showLoading() = progressbar?.visible()

    fun isLoading() = progressbar != null

    protected fun setContentViewVisibility(visibility: Boolean) {
        if (visibility) currentView?.visible() else currentView?.gone()
    }

    protected fun setErrorContentView(errorMsg: String) {
        loadErrorView(R.layout.error_content)
        tvErrorMsg?.text = errorMsg
        val toolBar = findViewById<Toolbar>(R.id.tool_bar)
        initToolbar(toolBar)
    }

    private fun initToolbar(toolBar: Toolbar) {
        setSupportActionBar(toolBar)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
    }

    private fun loadErrorView(layoutId: Int) {
        if (vsError != null) {
            vsError.layoutResource = layoutId
            currentView?.gone()
            currentView = vsError.inflate()
        }
        currentView?.let { loadView(it) }
    }


    /*Register broadcast receiver */
    private fun registerNetworkListener() {
        val intentFilter = IntentFilter(CONNECTIVITY_CHANGE)
        registerReceiver(networkChangeReceiver, intentFilter)
    }

    /*Network change listener*/
    private val networkChangeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val isOnline = isNetworkAvailable()
            if (!isOnline) {
                currentView?.snack(
                    getString(R.string.network_not_available),
                    Snackbar.LENGTH_SHORT
                ) {}
            }
        }
    }
}
