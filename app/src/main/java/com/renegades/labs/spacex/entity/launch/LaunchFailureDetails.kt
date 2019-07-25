package com.renegades.labs.spacex.entity.launch

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LaunchFailureDetails(
    @SerialName("time")
    val time: Long? = null,

    @SerialName("altitude")
    val altitude: Int? = null,

    @SerialName("reason")
    val reason: String? = null
)