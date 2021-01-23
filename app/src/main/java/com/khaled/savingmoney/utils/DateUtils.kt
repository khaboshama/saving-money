package com.khaled.savingmoney.utils

import com.khaled.savingmoney.constant.Constants
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getDateFormat(stringDate: String?, dateFormat: String?): String {
        var resultDate = ""
        stringDate?.let {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
            val date: Date? = sdf.parse(stringDate)
            date?.let {
                val dateStyle = dateFormat ?: Constants.DEFAULT_DATE_FORMATE
                val format = SimpleDateFormat(dateStyle, Locale.getDefault())
                resultDate = format.format(it)
            }
        }
        return resultDate
    }
}