package com.renegades.labs.spacex.entity.launch


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Core(

    @SerialName("block")
    val block: Int? = null,

    @SerialName("core_serial")
    val coreSerial: String? = null,

    @SerialName("flight")
    val flight: Int? = null,

    @SerialName("gridfins")
    val gridfins: Boolean? = null,

    @SerialName("land_success")
    val landSuccess: Boolean? = null,

    @SerialName("landing_intent")
    val landingIntent: Boolean? = null,

    @SerialName("landing_type")
    val landingType: String? = null,

    @SerialName("landing_vehicle")
    val landingVehicle: String? = null,

    @SerialName("legs")
    val legs: Boolean? = null,

    @SerialName("reused")
    val reused: Boolean? = null
) : java.io.Serializable