package com.nbc.youtube.data.source

import com.nbc.youtube.presentation.model.VideoEntity
import retrofit2.Call

// 원격 데이터 소소로서의 역할, 실제 네트워크 통신을 위한 구현은 이 인터페이스를 구현한 클래스에서 이루어지게 된다.
// YouTubeService를 기반으로 작성
interface YoutubeRemoteDataSource {
    fun getSearchVideo(query: String, apiKey: String): Call<VideoEntity>
    fun getPopularVideos(apiKey: String): Call<VideoEntity>
}