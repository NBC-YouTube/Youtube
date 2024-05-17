package com.nbc.youtube.presentation.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nbc.youtube.data.local.AppDatabase
import com.nbc.youtube.data.local.UserEntityDao
import com.nbc.youtube.data.local.VideoEntityDao
import com.nbc.youtube.data.repository.YoutubeRepository
import com.nbc.youtube.presentation.my.MyViewModel

class AppContainer(context: Context) {

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val db: AppDatabase by lazy {
        AppDatabase.getInstance(context, applicationScope)
    }

    fun provideVideoEntityDao(): VideoEntityDao {
        return db.videoEntityDao()
    }

    fun provideUserEntityDao(): UserEntityDao {
        return db.userEntityDao()
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