package com.nbc.youtube.presentation.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.nbc.youtube.data.local.AppDatabase
import com.nbc.youtube.data.local.VideoEntityDao
import com.nbc.youtube.data.repository.YoutubeRepository
import com.nbc.youtube.presentation.my.MyViewModel

class AppContainer(private val context: Context) {

    private val db: AppDatabase by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, "nbc-youtube")
            .build()
    }

    fun provideVideoEntityDao(): VideoEntityDao {
        return db.videoEntityDao()
    }

    fun provideYoutubeRepository(): YoutubeRepository {
        TODO()
    }

    fun createMyViewModelFactory(): ViewModelProvider.Factory {
        return object: ViewModelProvider.Factory {
            private val repository: YoutubeRepository = provideYoutubeRepository()

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MyViewModel(
                    repository
                ) as T
            }
        }
    }
}