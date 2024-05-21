package com.nbc.youtube.data.remote

import com.nbc.youtube.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://www.googleapis.com/youtube/v3/"

    private val moshi = Moshi.Builder()
        .add(DateAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val apiInterceptor = Interceptor {
        val originalRequest = it.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("x-goog-api-key", BuildConfig.YOUTUBE_API_KEY)
            .build()
        it.proceed(newRequest)
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(apiInterceptor)
        .build()

    val retrofitClient: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}