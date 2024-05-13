package com.nbc.youtube.data.source

import com.nbc.youtube.data.remote.network.YouTubeSearchResponse

/*interface YoutubeRemoteDataSource,
* class YoutubeRemoteDataSourceImpl을 작성하는 이유
* interface와 class를 분리함으로써, 유지보수성, 확장성 및 테스트 용이성이 향상된다.*/

interface YoutubeRemoteDataSource {
    suspend fun fetchSearchVideo( // 비동기적으로 나타낼 수 있음 suspend
        part: String, // 매개변수
        order: String,
        query: String,
        apiKey: String,
        safeSearch: String,
        maxResults: Int
    ): YouTubeSearchResponse

    suspend fun fetchPopularVideos(
        part: String,
        apiKey: String,
        regionCode: String,
        categoryId: String?,
        maxResults: Int
    ): YouTubeSearchResponse
}
