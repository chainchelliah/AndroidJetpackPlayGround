package com.sample.test.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.sample.test.R
import com.sample.test.ui.base.BaseActivity
import com.sample.test.ui.home.HomeActivity
import com.sample.test.utils.Constants.mDelay

/**
 * Created by SangiliPandian C on 23-11-2019.
 */
class SplashActivity : BaseActivity() {

    override val layoutResourceId = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        Handler().postDelayed({
            moveToHomePage()
        }, mDelay)
    }

    private fun moveToHomePage() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }
}
