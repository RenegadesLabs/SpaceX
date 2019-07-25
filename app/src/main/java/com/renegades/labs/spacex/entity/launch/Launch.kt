package com.renegades.labs.spacex.entity.launch


import androidx.recyclerview.widget.DiffUtil
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Launch(

    @SerialName("details")
    val details: String? = null,

    @SerialName("flight_number")
    val flightNumber: Int? = null,

    @SerialName("is_tentative")
    val isTentative: Boolean? = null,

    @SerialName("launch_date_local")
    val launchDateLocal: String? = null,

    @SerialName("launch_date_unix")
    val launchDateUnix: Long? = null,

    @SerialName("launch_date_utc")
    val launchDateUtc: String? = null,

    @SerialName("launch_site")
    val launchSite: LaunchSite? = null,

    @SerialName("launch_success")
    val launchSuccess: Boolean? = null,

    @SerialName("launch_failure_details")
    val launchFailureDetails: LaunchFailureDetails? = null,

    @SerialName("launch_window")
    val launchWindow: Int? = null,

    @SerialName("launch_year")
    val launchYear: String? = null,

    @SerialName("links")
    val links: Links? = null,

    @SerialName("mission_id")
    val missionId: List<String?>? = null,

    @SerialName("mission_name")
    val missionName: String? = null,

    @SerialName("rocket")
    val rocket: Rocket? = null,

    @SerialName("ships")
    val ships: List<String?>? = null,

    @SerialName("static_fire_date_unix")
    val staticFireDateUnix: Long? = null,

    @SerialName("static_fire_date_utc")
    val staticFireDateUtc: String? = null,

    @SerialName("tbd")
    val tbd: Boolean? = null,

    @SerialName("telemetry")
    val telemetry: Telemetry? = null,

    @SerialName("tentative_max_precision")
    val tentativeMaxPrecision: String? = null,

    @SerialName("timeline")
    val timeline: Timeline? = null,

    @SerialName("upcoming")
    val upcoming: Boolean? = null
) : java.io.Serializable {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Launch?>() {
            override fun areItemsTheSame(oldItem: Launch, newItem: Launch): Boolean {
                return oldItem.flightNumber == newItem.flightNumber
            }

            override fun areContentsTheSame(oldItem: Launch, newItem: Launch): Boolean {
                return oldItem == newItem
            }
        }
    }
}