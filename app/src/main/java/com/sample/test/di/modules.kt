@file:Suppress("USELESS_CAST")

package com.sample.test.di

import com.sample.test.data.api.HomeApi
import com.sample.test.data.domain.usecase.HomeUseCase
import com.sample.test.data.repo.HomeRepository
import com.sample.test.data.repo.HomeRepositoryImpl
import com.sample.test.db.AppDatabase
import com.sample.test.ui.home.HomeViewModel
import com.sample.test.utils.Constants.DB_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit


/**
 * Created by SangiliPandian C on 22-11-2019.
 */

fun injectDependencies() = loadModules

private val loadModules by lazy {
    loadKoinModules(listOf(viewModelModule, repositoryModule, netWorkModule, roomModule))
}

val viewModelModule = module {
    factory { HomeUseCase(get()) }
    viewModel { HomeViewModel(get(), get()) }
}

val repositoryModule = module {
    factory { HomeRepositoryImpl(get(), get()) as HomeRepository }
}

val netWorkModule = module {
    factory { (get() as Retrofit).create(HomeApi::class.java) }
}

val roomModule = module {
    single(named(DB_NAME)) { AppDatabase.buildDatabase(androidContext()) }
    factory { (get(named(DB_NAME)) as AppDatabase).homeDao() }
}
