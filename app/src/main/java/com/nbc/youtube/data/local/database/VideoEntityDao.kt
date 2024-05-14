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

@Dao
interface VideoEntityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) // 데이터를 삽입하는 함수, 충돌 시 기존 데이터를 대체
    suspend fun insertVideo(video: VideoEntity) // 비동기적으로 비디오 엔티티 데이터베이스에 삽입

    @Delete // 삭제
    suspend fun deleteVideo(video: VideoEntity)

    @Query("SELECT * FROM video_entitiy") // 데이터를 조회하는 쿼리를 정의
    fun getAllVideos(): LiveData<List<VideoEntity>> // 모든 데이터를 livedata 형태로 반환
}

@Database(entities = [VideoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() { // 추상 클래스
    abstract fun youtubeVideoDao(): VideoEntityDao // 인터페이스의 구현체를 반환

    companion object { // 싱글톤 패턴을 사용
        @Volatile private var instance: AppDatabase? = null // 싱글톤 인스턴스를 저장할 변수

        fun getDatabase(context: Context): AppDatabase = // 데이터베이스 인스턴스를 반환하는 함수
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "video_database"
                ).build().also { instance = it }
            }
    }
}
