package com.nbc.youtube.data.remote.network

import com.nbc.youtube.presentation.model.VideoEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeService {
    // 검색한 영상 보여주기
    @GET("youtube/v3/search") // 경로
    fun getSearchVideo(
        @Query("part") part: String, // API 응답에서 반환되어야 하는 데이터 부분
        @Query("order") order: String, // 검색 결과 정렬 순
        @Query("q") query: String, // youtube에서 검색하고자 하는 문자열
        @Query("key") apiKey: String, // api key
        @Query("type") type: String = "video", // 검색할 리소스의 타입
        @Query("safeSearch") safeSearch: String = "strict", // 필터링
        @Query("maxResults") maxResults: Int = 20 // 최대 보여줄 수 있는 아이템 갯수
    ): Call<VideoEntity>

    // 인기 있는 영상 보여주기
    @GET("youtube/v3/videos")
    fun getPopularVideos(
        @Query("part") part: String,
        @Query("chart") chart: String = "mostPopular", // 가장 인기있는 동영상
        @Query("regionCode") regionCode: String = "KR", // 인기영상의 국가 범위를 기본값으로 한국으로 설정
        @Query("key") apiKey: String, // api key
        // 카테고리를 필터링하려면 이 값을 설정, 필터링하지 않으려면 null
        @Query("videoCategoryId") categoryId: String? = null,
        @Query("maxResults") maxResults: Int = 25 // 사용하지 않을 시 최대 5개까지만 아이템이 나옴
    ): Call<VideoEntity>
}