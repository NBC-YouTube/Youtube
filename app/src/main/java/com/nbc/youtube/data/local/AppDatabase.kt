package com.nbc.youtube.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nbc.youtube.data.local.model.UserEntity
import com.nbc.youtube.data.local.model.VideoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [VideoEntity::class, UserEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun videoEntityDao(): VideoEntityDao
    abstract fun userEntityDao(): UserEntityDao

    companion object {
        @Volatile
        private var INSTNACE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTNACE ?: synchronized(this) {
                val instance = INSTNACE ?: Room.databaseBuilder(context, AppDatabase::class.java, "nbc-youtube.db")
                    .createFromAsset("database/userEntity.db")
                    .build()
                INSTNACE = instance
                instance
            }
        }

    }
}