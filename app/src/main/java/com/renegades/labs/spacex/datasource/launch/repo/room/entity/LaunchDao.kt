package com.renegades.labs.spacex.datasource.launch.repo.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LaunchDao(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "mission_name") val missionName: String?,
    @ColumnInfo(name = "upcoming") val upcoming: Boolean?,
    @ColumnInfo(name = "launch_date_unix") val launchDateUnix: Long?,
    @ColumnInfo(name = "rocketName") val rocketName: String?,
    @ColumnInfo(name = "details") val details: String?,
    @ColumnInfo(name = "flickr_images") val flickrImages: String?
)