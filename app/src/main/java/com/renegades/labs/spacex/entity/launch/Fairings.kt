package com.renegades.labs.spacex.entity.launch


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Fairings(

    @SerialName("recovered")
    val recovered: Boolean? = null,

    @SerialName("recovery_attempt")
    val recoveryAttempt: Boolean? = null,

    @SerialName("reused")
    val reused: Boolean? = null,

    @SerialName("ship")
    val ship: String? = null
)