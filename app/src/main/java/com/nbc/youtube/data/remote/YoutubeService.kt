package com.nbc.youtube.data.remote

import com.nbc.youtube.data.remote.model.response.Id
import com.nbc.youtube.data.remote.model.response.YoutubeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeService {

    @GET("search")
    suspend fun getSearchVideo(
        @Query("part") part: String,
        @Query("order") order: String, // 정렬 순서
        @Query("q") query: String, // 검색
        @Query("type") type: String, // 검색할 리소스 타입
        @Query("safeSearch") safeSearch: String, // 안전 검색어
        @Query("maxResults") maxResults: Int = 20
    ): YoutubeResponse<String>

    @GET("videos")
    suspend fun getPopularVideos(
        @Query("part") part: String = "snippet",
        @Query("chart") chart: String = "mostPopular",
        @Query("regionCode") regionCode: String = "kr",
        @Query("maxResults") maxResults: Int = 30,
    ): YoutubeResponse<String>

    @GET("search")
    suspend fun getCategoryVideos(
        @Query("part") part: String = "snippet",
        @Query("videoCategoryId") videoCategoryId: String, // 비디오 카테고리 ID
        @Query("type") type: String = "video",
        @Query("maxResults") maxResults: Int = 30,
    ): YoutubeResponse<Id>

    @GET("videoCategories")
    suspend fun getCategories(
        @Query("part") part: String = "snippet",
        @Query("regionCode") regionCode: String = "kr",
    ): YoutubeResponse<String>
}