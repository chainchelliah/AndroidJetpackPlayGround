package com.sample.test.utils.network.interceptor

import com.sample.test.utils.Constants.CONTENT_TYPE
import com.sample.test.utils.Constants.HEADER_STORE_KEY
import com.sample.test.utils.Constants.HEADER_STORE_VALUE
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by SangiliPandian C on 22-11-2019.
 */
class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(CONTENT_TYPE, "application/json")
            .addHeader(HEADER_STORE_KEY, HEADER_STORE_VALUE)
            .build()
        return chain.proceed(request)

    }

}
