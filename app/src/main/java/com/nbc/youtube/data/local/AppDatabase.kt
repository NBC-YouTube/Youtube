package com.nbc.youtube.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nbc.youtube.data.local.model.UserEntity
import com.nbc.youtube.presentation.model.UserInfo
import com.nbc.youtube.presentation.model.VideoInfo
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

@Database(entities = [VideoInfo::class, UserInfo::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun videoEntityDao(): VideoEntityDao
    abstract fun userEntityDao(): UserEntityDao

    companion object {
        @Volatile
        private var INSTNACE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTNACE ?: synchronized(this) {
                val instance = INSTNACE ?: Room.databaseBuilder(context, AppDatabase::class.java, "nbc-youtube")
                    .addCallback(object: Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            Executors.newSingleThreadExecutor().execute {
                                runBlocking {
                                    getInstance(context).userEntityDao().addUserEntity(UserEntity.userEntity)
                                }
                            }
                        }
                    })
                    .build()
                INSTNACE = instance
                instance
            }
        }

    }
}