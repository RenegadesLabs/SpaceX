package com.renegades.labs.spacex.entity.launch


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FirstStage(

    @SerialName("cores")
    val cores: List<Core?>? = null
) : java.io.Serializable