package com.renegades.labs.spacex.entity.launch


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Links(

    @SerialName("article_link")
    val articleLink: String? = null,

    @SerialName("flickr_images")
    val flickrImages: List<String?>? = null,

    @SerialName("mission_patch")
    val missionPatch: String? = null,

    @SerialName("mission_patch_small")
    val missionPatchSmall: String? = null,

    @SerialName("presskit")
    val presskit: String? = null,

    @SerialName("reddit_campaign")
    val redditCampaign: String? = null,

    @SerialName("reddit_launch")
    val redditLaunch: String? = null,

    @SerialName("reddit_media")
    val redditMedia: String? = null,

    @SerialName("reddit_recovery")
    val redditRecovery: String? = null,

    @SerialName("video_link")
    val videoLink: String? = null,

    @SerialName("wikipedia")
    val wikipedia: String? = null,

    @SerialName("youtube_id")
    val youtubeId: String? = null
) : java.io.Serializable