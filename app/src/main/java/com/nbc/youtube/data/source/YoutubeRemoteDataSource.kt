package com.nbc.youtube.data.source

import com.nbc.youtube.data.remote.network.YouTubeSearchResponse

/*interface YoutubeRemoteDataSource,
* class YoutubeRemoteDataSourceImpl을 작성하는 이유
* interface와 class를 분리함으로써, 유지보수성, 확장성 및 테스트 용이성이 향상된다.*/

interface YoutubeRemoteDataSource {
    // Youtube api를 통해 비디오 검색과 인기 비디오 목록을 가져오기 위한 메서드들을 정의
    suspend fun getSearchVideo(
        part: String,
        order: String,
        query: String,
        apiKey: String,
        type: String = "video",
        safeSearch: String = "strict",
        maxResults: Int = 20
    ): YouTubeSearchResponse

    suspend fun getPopularVideos(
        part: String,
        chart: String = "mostPopular",
        regionCode: String = "KR",
        apiKey: String,
        maxResults: Int = 20
    ): YouTubeSearchResponse

    suspend fun getCategoryVideos(
        part: String,
        chart: String,
        regionCode: String = "KR",
        videoCategoryId: String? = null,
        apiKey: String,
        maxResults: Int= 20
    ): YouTubeSearchResponse
}

