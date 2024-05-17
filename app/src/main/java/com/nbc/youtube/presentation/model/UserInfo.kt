package com.nbc.youtube.presentation.model

import android.net.Uri
import com.nbc.youtube.data.local.model.UserEntity
import java.util.UUID

data class UserInfo(
    val name: String,
    val profileThumbnail: Uri,
    val channelThumbnail: Uri,
    val introduction: String,
    val id: String = UUID.randomUUID().toString(),
) {
    fun toEntity(): UserEntity = UserEntity(
        name = name,
        profileThumbnail = profileThumbnail,
        channelThumbnail = channelThumbnail,
        introduction = introduction,
        id = id,
    )
}
