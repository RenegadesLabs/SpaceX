package com.renegades.labs.spacex.datasource.launch.repo.network

import com.renegades.labs.spacex.datasource.launch.repo.LaunchRepo
import com.renegades.labs.spacex.entity.Result
import com.renegades.labs.spacex.entity.launch.Launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class NetworkLaunchRepo : LaunchRepo, KoinComponent {

    private val spacexService by inject<SpacexService>()

    override fun getLaunches(count: Int, offset: Int): Result<List<Launch>> {
        return try {
            val response = spacexService.getLaunches(count, offset).execute()

            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(RuntimeException(response.errorBody().toString()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}