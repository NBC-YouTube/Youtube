package com.nbc.youtube.data.remote.model

import com.nbc.youtube.presentation.model.CategoryInfo

data class CategoryEntity(
    val name: String,
    val id: String,
) {
    fun toPresentation(): CategoryInfo = CategoryInfo(
        name = name,
        id = id,
    )
}
