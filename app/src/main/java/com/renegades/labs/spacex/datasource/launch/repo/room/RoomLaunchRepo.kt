package com.renegades.labs.spacex.datasource.launch.repo.room

import com.renegades.labs.spacex.datasource.launch.repo.LaunchRepo
import com.renegades.labs.spacex.entity.Result
import com.renegades.labs.spacex.entity.launch.Launch

class RoomLaunchRepo : LaunchRepo {

    override fun getLaunches(count: Int, offset: Int): Result<List<Launch>> {
        TODO()
    }
}