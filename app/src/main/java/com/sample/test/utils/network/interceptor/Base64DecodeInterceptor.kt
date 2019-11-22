package com.sample.test.utils.network.interceptor

import com.sample.test.utils.Constants.CONTENT_TYPE
import com.sample.test.utils.Constants.HEADER_STORE_KEY
import com.sample.test.utils.Constants.HEADER_STORE_VALUE
import com.sample.test.utils.decodeToString
import com.sample.test.utils.readAllBytes
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

/**
 * Created by SangiliPandian C on 22-11-2019.
 */
class Base64DecodeInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(CONTENT_TYPE, "application/json")
            .addHeader(HEADER_STORE_KEY, HEADER_STORE_VALUE)
            .build()
        val response = chain.proceed(request)
        val byteIs = response.body!!.byteStream()
        val byteArray = byteIs.readAllBytes()
        val output = decodeToString(byteArray)
        Timber.d("Interceptor Decode Value  $output")
        return response

    }

}
