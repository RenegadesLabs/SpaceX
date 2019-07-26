package com.renegades.labs.spacex.di

import androidx.paging.DataSource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.renegades.labs.spacex.datasource.paging.LaunchDataSourceFactory
import com.renegades.labs.spacex.datasource.repo.LaunchRepo
import com.renegades.labs.spacex.datasource.repo.network.NetworkLaunchRepo
import com.renegades.labs.spacex.datasource.repo.network.SpaceXService
import com.renegades.labs.spacex.entity.launch.Launch
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

@UnstableDefault
val module = module {

    single<Interceptor> { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get())
            .build()
    }

    single<SpaceXService> {
        Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/v3/")
            .client(get())
            .addConverterFactory(Json.nonstrict.asConverterFactory(MediaType.get("application/json")))
            .build()
            .create(SpaceXService::class.java)
    }

    single<DataSource.Factory<Int, Launch>> { LaunchDataSourceFactory() }

    single<LaunchRepo> { NetworkLaunchRepo() }

}