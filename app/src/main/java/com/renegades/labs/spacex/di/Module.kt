package com.renegades.labs.spacex.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.renegades.labs.spacex.datasource.launch.paging.LaunchDataSourceFactory
import com.renegades.labs.spacex.datasource.launch.repo.LaunchRepo
import com.renegades.labs.spacex.datasource.launch.repo.network.NetworkLaunchRepo
import com.renegades.labs.spacex.datasource.launch.repo.network.SpacexService
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

    single<SpacexService> {
        Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/v3/")
            .client(get())
            .addConverterFactory(Json.nonstrict.asConverterFactory(MediaType.get("application/json")))
            .build()
            .create(SpacexService::class.java)
    }

    single<LaunchDataSourceFactory> { LaunchDataSourceFactory() }

    single<LaunchRepo> { NetworkLaunchRepo() }

}