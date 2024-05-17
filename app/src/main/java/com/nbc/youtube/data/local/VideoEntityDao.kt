package com.nbc.youtube.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nbc.youtube.data.model.VideoEntity

@Dao
interface VideoEntityDao {

    @Query("SELECT * FROM videos")
    suspend fun getFavoriteVideos(): List<VideoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteVideo(videoEntity: VideoEntity)

    @Delete
    suspend fun removeFavoriteVideo(videoEntity: VideoEntity)
}