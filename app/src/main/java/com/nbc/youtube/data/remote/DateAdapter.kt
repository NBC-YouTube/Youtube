package com.nbc.youtube.data.remote

import com.nbc.youtube.data.local.DateUtil
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.util.Date

class DateAdapter {
    @ToJson
    fun toJson(datetime: Date): String {
        return DateUtil.formatDate(datetime)
    }

    @FromJson
    fun fromJson(datetime: String): Date {
        return DateUtil.parseDate(datetime)
    }
}