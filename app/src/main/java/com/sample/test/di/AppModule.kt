package com.sample.test.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.sample.test.BuildConfig.BASE_URL
import com.sample.test.BuildConfig.DEBUG
import com.sample.test.utils.Constants.CONNECT_TIMEOUT
import com.sample.test.utils.Constants.READ_TIMEOUT
import com.sample.test.utils.network.AppDispatchers
import com.sample.test.utils.network.interceptor.Base64DecodeInterceptor
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by SangiliPandian C on 22-11-2019.
 */

val appmodule = module {

    single { Gson() }

    single { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }

    single {
        val clientBuilder = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MINUTES)
            .readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)
            .addInterceptor(Base64DecodeInterceptor())
        if (DEBUG) {
            clientBuilder.addNetworkInterceptor(StethoInterceptor())
        }
        clientBuilder.build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
