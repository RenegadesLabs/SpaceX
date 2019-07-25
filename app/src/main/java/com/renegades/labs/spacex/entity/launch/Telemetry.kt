package com.renegades.labs.spacex.entity.launch


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Telemetry(

    @SerialName("flight_club")
    val flightClub: String? = null
)