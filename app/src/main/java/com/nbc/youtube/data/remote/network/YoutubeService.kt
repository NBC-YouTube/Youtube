package com.nbc.youtube.data.remote.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeService {
    @GET("youtube/v3/search") // 검색 엔드 포인트
    suspend fun getSearchVideo( // suspend는 비동기적으로 실행하기 위한 키워드
        @Query("part") part: String,
        @Query("order") order: String, // 정렬 순서
        @Query("q") query: String, // 검색
        @Query("key") apiKey: String,
        @Query("type") type: String = "video", // 검색할 리소스 타입
        @Query("safeSearch") safeSearch: String = "strict", // 안전 검색어
        @Query("maxResults") maxResults: Int = 20
    ): YouTubeSearchResponse // 이제 Call을 반환하지 않고, 직접 YouTubeSearchResponse를 반환합니다.

    @GET("youtube/v3/videos")
    suspend fun getPopularVideos(
        @Query("part") part: String,
        @Query("chart") chart: String = "mostPopular", // 인기 있는 비디오 목록 요청
        @Query("regionCode") regionCode: String = "KR", // 특정 지역
        @Query("key") apiKey: String,
        @Query("videoCategoryId") categoryId: String? = null, // 특정 카테고리의 비디오만 요청
        @Query("maxResults") maxResults: Int = 25 // 최대 아이템 갯수
    ): YouTubeSearchResponse // 마찬가지로 Call 대신 YouTubeSearchResponse를 반환합니다.
}