package com.renegades.labs.spacex.datasource.repo

import com.renegades.labs.spacex.entity.Result
import com.renegades.labs.spacex.entity.launch.Launch

interface LaunchRepo {

    fun getLaunches(count: Int, offset: Int): Result<List<Launch>>

}