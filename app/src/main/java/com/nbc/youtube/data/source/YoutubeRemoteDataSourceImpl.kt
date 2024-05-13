package com.nbc.youtube.data.source

import com.nbc.youtube.data.remote.network.YouTubeSearchResponse
import com.nbc.youtube.data.remote.network.YouTubeService
import retrofit2.awaitResponse

/*YoutubeRemoteDataSource 인터페이스의 구현체이다
* Youtube 데이터를 비동기적으로 가져오는 두 가지 주요 기능을 구현한다
* 이 인터페이스는 실제 http요청을 수행하는 메서드를 정의해야한다.*/
// YoutubeRemoteDataSource 인터페이스의 구현체
class YoutubeRemoteDataSourceImpl(private val youtubeService: YouTubeService // YoutubeService 주입
) : YoutubeRemoteDataSource {

    override suspend fun fetchSearchVideo(
        part: String,
        order: String,
        query: String,
        apiKey: String,
        type: String,
        safeSearch: String,
        maxResults: Int
    ): YouTubeSearchResponse {
        // 실제 네트워크 요청을 실행하고 결과를 반환
        return youtubeService.getSearchVideo(part, order, query, apiKey, type, safeSearch, maxResults)
    }

    override suspend fun fetchPopularVideos(
        part: String,
        apiKey: String,
        chart: String,
        regionCode: String,
        categoryId: String?,
        maxResults: Int
    ): YouTubeSearchResponse {
        // 실제 네트워크 요청을 실행하고 결과를 반환
        return youtubeService.getPopularVideos(part, apiKey, chart, regionCode, categoryId, maxResults)
    }
}