package com.nbc.youtube.data.source

import com.nbc.youtube.data.remote.network.YouTubeSearchResponse
import com.nbc.youtube.data.remote.network.YouTubeService
import retrofit2.awaitResponse

/*YoutubeRemoteDataSource 인터페이스의 구현체이다
* Youtube 데이터를 비동기적으로 가져오는 두 가지 주요 기능을 구현한다
* 이 인터페이스는 실제 http요청을 수행하는 메서드를 정의해야한다.*/
// YoutubeRemoteDataSource 인터페이스의 구현체
class YoutubeRemoteDataSourceImpl(
    private val youtubeService: YouTubeService, // Retrofit을 통해 생성된 YouTubeService 인터페이스의 인스턴스
    private val apiKey: String // YouTube API 키
) : YoutubeRemoteDataSource {

    override suspend fun getSearchVideo(
        part: String,
        order: String,
        query: String,
        apiKey: String,
        type: String,
        safeSearch: String,
        maxResults: Int
    ): YouTubeSearchResponse {
        return youtubeService.getSearchVideo(part, order, query, this.apiKey, type, safeSearch, maxResults)
    }

    override suspend fun getPopularVideos(
        part: String,
        chart: String,
        regionCode: String,
        apiKey: String,
        maxResults: Int
    ): YouTubeSearchResponse {
        return youtubeService.getPopularVideos(part, chart, regionCode, this.apiKey, maxResults)
    }

    override suspend fun getCategoryVideos(
        part: String,
        chart: String,
        regionCode: String,
        videoCategoryId: String?,
        apiKey: String,
        maxResults: Int
    ): YouTubeSearchResponse {
        return youtubeService.getCategoryVideos(part, chart, regionCode,
            videoCategoryId, this.apiKey, maxResults)
    }
}

