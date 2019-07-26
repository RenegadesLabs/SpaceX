package com.renegades.labs.spacex.datasource.paging

import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import com.renegades.labs.spacex.datasource.repo.LaunchRepo
import com.renegades.labs.spacex.entity.launch.Launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class LaunchDataSource : PositionalDataSource<Launch>(), KoinComponent {

    private val launchRepo by inject<LaunchRepo>()

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Launch>) {
        val launchesResult = launchRepo.getLaunches(params.requestedLoadSize, params.requestedStartPosition)

        if (launchesResult.isSuccess) {
            callback.onResult(launchesResult.getOrNull()!!, 0, TOTAL_LAUNCHES)
        } else {
            throw launchesResult.exceptionOrNull()!!
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Launch>) {
        val launchesResult = launchRepo.getLaunches(params.loadSize, params.startPosition)
        if (launchesResult.isSuccess) {
            callback.onResult(launchesResult.getOrNull()!!)
        }
    }

    companion object {
        const val TOTAL_LAUNCHES = 103
    }
}

class LaunchDataSourceFactory : DataSource.Factory<Int, Launch>() {

    override fun create(): DataSource<Int, Launch> {
        return LaunchDataSource()
    }

}