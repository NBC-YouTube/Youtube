package com.nbc.youtube.presentation.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nbc.youtube.data.local.AppDatabase
import com.nbc.youtube.data.local.UserEntityDao
import com.nbc.youtube.data.local.VideoEntityDao
import com.nbc.youtube.data.remote.RetrofitClient
import com.nbc.youtube.data.remote.YoutubeRemoteDataSource
import com.nbc.youtube.data.remote.YoutubeRemoteDataSourceImpl
import com.nbc.youtube.data.remote.YoutubeService
import com.nbc.youtube.data.repository.YoutubeRepository
import com.nbc.youtube.data.repository.YoutubeRepositoryImpl
import com.nbc.youtube.presentation.home.videmodels.HomeViewModel
import com.nbc.youtube.presentation.my.MyViewModel
import retrofit2.Retrofit

class AppContainer(context: Context) {

    private val db: AppDatabase by lazy {
        AppDatabase.getInstance(context)
    }

    private fun provideVideoEntityDao(): VideoEntityDao {
        return db.videoEntityDao()
    }

    private fun provideUserEntityDao(): UserEntityDao {
        return db.userEntityDao()
    }

    private val retrofitClient: Retrofit by lazy {
        RetrofitClient.retrofitClient
    }

    private fun provideYoutubeService(retrofit: Retrofit): YoutubeService {
        return retrofit.create(YoutubeService::class.java)
    }

    private fun provideYoutubeRemoteDataSource(youtubeService: YoutubeService): YoutubeRemoteDataSource {
        return YoutubeRemoteDataSourceImpl(youtubeService)
    }

    fun provideYoutubeRepository(): YoutubeRepository {
        return YoutubeRepositoryImpl(
            provideYoutubeRemoteDataSource(provideYoutubeService(retrofitClient)),
            provideVideoEntityDao(),
            provideUserEntityDao(),
        )
    }

    fun createViewModelFactory(): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            private val repository: YoutubeRepository = provideYoutubeRepository()

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return when(modelClass) {
                    MyViewModel::class.java -> MyViewModel(repository)
                    HomeViewModel::class.java -> HomeViewModel(repository)
                    else -> throw IllegalArgumentException()
                } as T
            }
        }
    }
}