package com.renegades.labs.spacex.entity.launch


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Payload(

    @SerialName("customers")
    val customers: List<String?>? = null,

    @SerialName("manufacturer")
    val manufacturer: String? = null,

    @SerialName("nationality")
    val nationality: String? = null,

    @SerialName("norad_id")
    val noradId: List<Int?>? = null,

    @SerialName("orbit")
    val orbit: String? = null,

    @SerialName("orbit_params")
    val orbitParams: OrbitParams? = null,

    @SerialName("payload_id")
    val payloadId: String? = null,

    @SerialName("payload_mass_kg")
    val payloadMassKg: Float? = null,

    @SerialName("payload_mass_lbs")
    val payloadMassLbs: Float? = null,

    @SerialName("payload_type")
    val payloadType: String? = null,

    @SerialName("reused")
    val reused: Boolean? = null
) : java.io.Serializable