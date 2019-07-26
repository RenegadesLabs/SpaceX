package com.renegades.labs.spacex.entity.launch


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LaunchSite(

    @SerialName("site_id")
    val siteId: String? = null,

    @SerialName("site_name")
    val siteName: String? = null,

    @SerialName("site_name_long")
    val siteNameLong: String? = null
) : java.io.Serializable