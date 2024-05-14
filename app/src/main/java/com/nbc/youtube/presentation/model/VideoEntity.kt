package com.nbc.youtube.presentation.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * @param releaseDate 업로드 날짜
 * @param channelId 채널 고유 ID
 * @param channelTitle 채널이름
 * @param title 영상 제목
 * @param description 영상 설명
 * @param id 비디오 고유 ID
 * @param thumbnail 썸네일 주소, Repository에서 화질이 제일 좋은 아이템의 URL로 넘겨주세요.
 * @param categoryId 영상의 카테고리 값
 */
@Entity(tableName = "video_entitiy")
@Parcelize
data class VideoEntity (
    val releaseDate: String,
    val channelTitle: String,
    val title: String,
    val description: String,
    val thumbnail: String,
    val categoryId: String
): Parcelable