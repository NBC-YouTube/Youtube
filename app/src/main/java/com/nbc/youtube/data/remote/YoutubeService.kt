package com.nbc.youtube.data.remote

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
    ): YoutubeResponse

    @GET("videos")
    suspend fun getPopularVideos(
        @Query("part") part: String = "snippet",
        @Query("chart") chart: String = "mostPopular",
        @Query("regionCode") regionCode: String = "kr",
        @Query("maxResults") maxResults: Int = 30,
    ): YoutubeResponse

    @GET("videos")
    suspend fun getCategoryVideos(
        @Query("part") part: String,
        @Query("chart") chart: String = "mostPopular", // 인기 있는 비디오 목록 요청
        @Query("regionCode") regionCode: String = "kr", // 특정 지역
        @Query("videoCategoryId") videoCategoryId: String? = null, // 비디오 카테고리 ID
        @Query("maxResults") maxResults: Int = 30 // 최대 아이템 갯수
    ): YoutubeResponse

    @GET("videoCategories")
    suspend fun getCategories(
        @Query("part") part: String = "snippet",
        @Query("regionCode") regionCode: String = "kr",
    ): YoutubeResponse
}