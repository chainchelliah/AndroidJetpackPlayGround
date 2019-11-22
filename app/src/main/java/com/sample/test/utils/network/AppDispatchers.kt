package com.sample.test.utils.network

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by SangiliPandian C on 22-11-2019.
 */

class AppDispatchers(
    val main: CoroutineDispatcher,
    val io: CoroutineDispatcher
)
