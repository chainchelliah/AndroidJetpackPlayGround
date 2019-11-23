package com.sample.test.data.domain.usecase

import com.sample.test.data.repo.HomeRepository

/**
 * Created by SangiliPandian C on 22-11-2019.
 */

class HomeUseCase(private val repo: HomeRepository) {
    suspend fun getData() = repo.loadData()
}
