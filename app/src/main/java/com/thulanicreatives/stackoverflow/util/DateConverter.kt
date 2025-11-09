package com.thulanicreatives.stackoverflow.util

import java.util.Calendar
import java.util.Locale

fun convertUnixToDateTime(date:Long?):String {
    if( date == null) return ""
    val calendar = Calendar.getInstance(Locale.getDefault())
    calendar.timeInMillis = date * TIME_CONVERTER
    return android.text.format.DateFormat.format("MMM d yy", calendar).toString()
}

const val TIME_CONVERTER = 1000L
