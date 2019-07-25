package com.renegades.labs.spacex.entity.launch


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrbitParams(

    @SerialName("apoapsis_km")
    val apoapsisKm: Double? = null,

    @SerialName("arg_of_pericenter")
    val argOfPericenter: Double? = null,

    @SerialName("eccentricity")
    val eccentricity: Double? = null,

    @SerialName("epoch")
    val epoch: String? = null,

    @SerialName("inclination_deg")
    val inclinationDeg: Double? = null,

    @SerialName("lifespan_years")
    val lifespanYears: Double? = null,

    @SerialName("longitude")
    val longitude: Double? = null,

    @SerialName("mean_anomaly")
    val meanAnomaly: Double? = null,

    @SerialName("mean_motion")
    val meanMotion: Double? = null,

    @SerialName("periapsis_km")
    val periapsisKm: Double? = null,

    @SerialName("period_min")
    val periodMin: Double? = null,

    @SerialName("raan")
    val raan: Double? = null,

    @SerialName("reference_system")
    val referenceSystem: String? = null,

    @SerialName("regime")
    val regime: String? = null,

    @SerialName("semi_major_axis_km")
    val semiMajorAxisKm: Double? = null
)