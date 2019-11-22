package com.sample.test.data.domain.model

/**
 * Created by SangiliPandian C on 25-07-2019.
 */

data class GenericReqAndResp<T>(val data: T, val message: String, val success: Boolean)
