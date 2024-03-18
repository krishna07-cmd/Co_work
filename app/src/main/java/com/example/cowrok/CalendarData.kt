package com.example.cowrok

import android.provider.ContactsContract.Data
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

data class CalendarData(val data:Date,var isSelected: Boolean=false){
    val calendarDay:String
        get() = SimpleDateFormat("EE", Locale.getDefault()).format(data)

    val calendarDate:String

        get() {
            val cal = Calendar.getInstance()
            cal.time=data
            return cal[Calendar.DAY_OF_MONTH].toString()
        }


}
