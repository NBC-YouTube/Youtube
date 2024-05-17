package com.nbc.youtube.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nbc.youtube.presentation.model.VideoInfo

@Entity(tableName = "videos")
data class VideoEntity(
    @ColumnInfo("release_date") val releaseDate: String,
    @PrimaryKey val id: String,
    @ColumnInfo("channel_title") val channelTitle: String,
    val title: String,
    val description: String,
    val thumbnail: String,
    @ColumnInfo("category_id") val categoryId: String,
) {
    fun toPresentation(): VideoInfo = VideoInfo(
        releaseDate = releaseDate,
        id = id,
        channelTitle = channelTitle,
        title = title,
        description = description,
        thumbnail = thumbnail,
        categoryId = categoryId
    )
}
