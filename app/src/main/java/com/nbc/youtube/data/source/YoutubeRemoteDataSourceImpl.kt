package com.nbc.youtube.data.source

import com.nbc.youtube.data.remote.network.YouTubeSearchResponse
import com.nbc.youtube.data.remote.network.YouTubeService
import retrofit2.awaitResponse

/*YoutubeRemoteDataSource 인터페이스의 구현체이다
* Youtube 데이터를 비동기적으로 가져오는 두 가지 주요 기능을 구현한다
* 이 인터페이스는 실제 http요청을 수행하는 메서드를 정의해야한다.*/
// YoutubeRemoteDataSource 인터페이스의 구현체
class YoutubeRemoteDataSourceImpl(private val youtubeService: YouTubeService) : YoutubeRemoteDataSource {

    override suspend fun fetchSearchVideo(
        part: String,
        order: String,
        query: String,
        apiKey: String,
        safeSearch: String,
        maxResults: Int
    ): YouTubeSearchResponse {
        // Retrofit의 suspend 함수 사용으로 execute() 및 body() 호출 제거
        return youtubeService.getSearchVideo(part, order, query, apiKey, safeSearch, maxResults.toString())
    }

    override suspend fun fetchPopularVideos(
        part: String,
        apiKey: String,
        regionCode: String,
        categoryId: String?,
        maxResults: Int
    ): YouTubeSearchResponse {
        // 마찬가지로, 직접적인 응답 반환
        return youtubeService.getPopularVideos(part, apiKey, regionCode, categoryId.toString(), maxResults.toString())
    }
}