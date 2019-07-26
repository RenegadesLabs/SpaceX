package com.renegades.labs.spacex.entity.launch


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Timeline(

    @SerialName("engine_chill")
    val engineChill: Int? = null,

    @SerialName("fairing_deploy")
    val fairingDeploy: Int? = null,

    @SerialName("first_stage_entry_burn")
    val firstStageEntryBurn: Int? = null,

    @SerialName("first_stage_landing")
    val firstStageLanding: Int? = null,

    @SerialName("go_for_launch")
    val goForLaunch: Int? = null,

    @SerialName("go_for_prop_loading")
    val goForPropLoading: Int? = null,

    @SerialName("ignition")
    val ignition: Int? = null,

    @SerialName("liftoff")
    val liftoff: Int? = null,

    @SerialName("maxq")
    val maxq: Int? = null,

    @SerialName("meco")
    val meco: Int? = null,

    @SerialName("payload_deploy")
    val payloadDeploy: Int? = null,

    @SerialName("prelaunch_checks")
    val prelaunchChecks: Int? = null,

    @SerialName("propellant_pressurization")
    val propellantPressurization: Int? = null,

    @SerialName("rp1_loading")
    val rp1Loading: Int? = null,

    @SerialName("seco-1")
    val seco1: Int? = null,

    @SerialName("seco-2")
    val seco2: Int? = null,

    @SerialName("second_stage_ignition")
    val secondStageIgnition: Int? = null,

    @SerialName("second_stage_restart")
    val secondStageRestart: Int? = null,

    @SerialName("stage1_lox_loading")
    val stage1LoxLoading: Int? = null,

    @SerialName("stage2_lox_loading")
    val stage2LoxLoading: Int? = null,

    @SerialName("stage_sep")
    val stageSep: Int? = null,

    @SerialName("webcast_liftoff")
    val webcastLiftoff: Int? = null
) : java.io.Serializable