package com.renegades.labs.spacex.datasource.repo.network

import com.renegades.labs.spacex.entity.launch.Launch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceXService {

    @GET("launches")
    fun getLaunches(@Query("limit") count: Int, @Query("offset") offset: Int): Call<List<Launch>>

}