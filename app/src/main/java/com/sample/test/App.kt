package com.sample.test

import android.app.Application
import android.content.Context
import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import com.sample.test.BuildConfig.DEBUG
import com.sample.test.di.appmodule
import com.sample.test.utils.PreferenceHelper
import io.fabric.sdk.android.Fabric
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * Created by SangiliPandian C on 22-11-2019.
 */
class App : Application() {

    companion object {
        lateinit var instance: App private set
        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initFabric() // Crashlytics
        initKoin()
        initTimber()
        initStetho(this)
        PreferenceHelper.init(this)
    }

    private fun initFabric() {
        val fabric = Fabric.Builder(this).kits(Crashlytics()).debuggable(true).build()
        Fabric.with(fabric)
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appmodule)
        }
    }

    private fun initTimber() {
        if (DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    fun initStetho(context: Context) {
        if (DEBUG) {
            Stetho.initializeWithDefaults(context.applicationContext)
        }
    }
}
