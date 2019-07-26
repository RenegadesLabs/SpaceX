package com.renegades.labs.spacex.entity.launch


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rocket(

    @SerialName("fairings")
    val fairings: Fairings? = null,

    @SerialName("first_stage")
    val firstStage: FirstStage? = null,

    @SerialName("rocket_id")
    val rocketId: String? = null,

    @SerialName("rocket_name")
    val rocketName: String? = null,

    @SerialName("rocket_type")
    val rocketType: String? = null,

    @SerialName("second_stage")
    val secondStage: SecondStage? = null
) : java.io.Serializable