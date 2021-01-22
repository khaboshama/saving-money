package com.khaled.savingmoney.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getDateFormat(stringDate: String, dateStyle: String): String {
        // 2021-01-22T08:15:40+00:00
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
        val date: Date? = sdf.parse(stringDate)
        var resultDate = ""
        date?.let {
            val format = SimpleDateFormat(dateStyle, Locale.getDefault())
            resultDate = format.format(it)
        }
        return resultDate
    }
}