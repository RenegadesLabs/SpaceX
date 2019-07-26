package com.renegades.labs.spacex.entity.launch


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SecondStage(

    @SerialName("block")
    val block: Int? = null,

    @SerialName("payloads")
    val payloads: List<Payload?>? = null
) : java.io.Serializable