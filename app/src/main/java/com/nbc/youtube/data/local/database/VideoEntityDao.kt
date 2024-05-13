package com.nbc.youtube.data.local.database

import android.content.Context
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nbc.youtube.presentation.model.VideoEntity
import kotlinx.parcelize.Parcelize

@Dao
interface VideoEntityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(video: VideoEntity)

    @Delete
    suspend fun deleteVideo(video: VideoEntity)

    @Query("SELECT * FROM video_entitiy")
    fun getAllVideos(): LiveData<List<VideoEntity>>
}

@Database(entities = [VideoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun youtubeVideoDao(): VideoEntityDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "video_database"
                ).build().also { instance = it }
            }
    }
}
