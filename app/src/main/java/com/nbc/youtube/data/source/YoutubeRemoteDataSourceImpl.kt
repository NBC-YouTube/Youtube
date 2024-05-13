package com.nbc.youtube.data.source

import com.nbc.youtube.data.remote.network.YouTubeService
import com.nbc.youtube.presentation.model.VideoEntity
import retrofit2.Call

// YoutubeRemoteDataSource 인터페이스를 구현한 것이다. Youtube API를 실제로 호출하여 데이터를 가져오는 구현체이다
// youTubeService는 YouTube API에 접근하기 위해 사용되는 Retrofit서비스이다.
// YouTubeService를 기반으로 작성
// date는 날짜순을 의미
class YoutubeRemoteDataSourceImpl(val youTubeService: YouTubeService): YoutubeRemoteDataSource {
    override fun getSearchVideo(query: String, apiKey: String): Call<VideoEntity> {
        return youTubeService.getSearchVideo(part = "snippet", order = "date", query = query, apiKey = apiKey)
    }

    override fun getPopularVideos(apiKey: String): Call<VideoEntity> {
        return youTubeService.getPopularVideos(part = "snippet", apiKey = apiKey)
    }
}