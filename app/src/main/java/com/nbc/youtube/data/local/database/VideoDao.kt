package com.nbc.youtube.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import com.nbc.youtube.presentation.model.VideoEntity

/*유튜브를 저장, 조회, 삭제하는 기능*/
@Dao
interface VideoDao {
    @Query("SELECT videoId, title, description, thumbnail, channelId FROM VideoEntity")
    fun getVideos(): LiveData<List<VideoEntity>> // 비디오의 아이디, 제목, 설명만 반환

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(video: VideoEntity) // 삽입하려는 기본 키가 이미 데이터베이스에 존재할 경우, 기존객체를 새 객체로 대체

    @Delete
    suspend fun delete(video: VideoEntity)
}

// room 데이터베이스
@Database(entities = [VideoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao
}