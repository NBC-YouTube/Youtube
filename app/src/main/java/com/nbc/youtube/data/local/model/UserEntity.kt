package com.nbc.youtube.data.local.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nbc.youtube.presentation.model.UserInfo


@Entity(tableName = "users")
data class UserEntity (
    val name: String,
    @ColumnInfo("profile_thumbnail") val profileThumbnail: Uri,
    @ColumnInfo("channel_thumbnail") val channelThumbnail: Uri,
    val introduction: String,
    @PrimaryKey val id: String,
) {

    fun toPresentation(): UserInfo = UserInfo(
        name = name,
        profileThumbnail = profileThumbnail,
        channelThumbnail = channelThumbnail,
        introduction = introduction,
        id = id,
    )
    companion object {
        /**
         * 초기 유저 ID를 나타냅니다.
         * assets userEntity.db 파일에서 초기 유저 정보를 넣고 있으며, 이 유저의 ID와 동일합니다.
         */
        const val DEFAULT_USER_ID = "1"
    }
}