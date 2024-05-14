package com.nbc.youtube.data.remote.network

import com.nbc.youtube.presentation.model.VideoEntity


data class YouTubeSearchResponse( // 검색 결과
    val items: List<VideoEntity>,
)

/*
data class Item( // 각 개별 아이템
    val id: Id,
    val snippet: Snippet,
)

data class Id(
    val videoId: String // 고유한 비디오의 아이디
)

data class Snippet( // 해당 비디오의 세부 정보
    val publishedAt: String, // 게시된 날짜
    val channelId: String, // 체널의 id
    val title: String, // 비디오의 제목
    val description: String, // 비디오의 설명
    val thumbnails: Thumbnails, // 썸네일 이미지
    val channelTitle: String, // 채널 제목
)

data class Thumbnails(
    val default: ThumbnailDetail,
    val medium: ThumbnailDetail,
    val high: ThumbnailDetail
)

data class ThumbnailDetail(
    val url: String,
)*/
