package com.inik.myweather

import android.util.Log
import java.time.LocalDateTime
import java.time.LocalTime

data class BaseDateTime(
    val baseDate: String,
    val baseTime: String,
){
    companion object {
        fun getBaseDateTime(): BaseDateTime{
            var dateTime = LocalDateTime.now()

           val baseTime = when(dateTime.toLocalTime()){
                in LocalTime.of(0,0) .. LocalTime.of(2,30) ->{
                    dateTime = dateTime.minusDays(1)
                    "2300"
                }

               in LocalTime.of(2,30) .. LocalTime.of(5,30) -> "0200"
               in LocalTime.of(2,30) .. LocalTime.of(8,30) -> "0500"
               in LocalTime.of(8,30) .. LocalTime.of(11,30) -> "0800"
               in LocalTime.of(11,30) .. LocalTime.of(12,30) -> "1100"
               in LocalTime.of(14,30) .. LocalTime.of(17,30) -> "1400"
               in LocalTime.of(17,30) .. LocalTime.of(20,30) -> "1700"
               in LocalTime.of(20,30) .. LocalTime.of(23,30) -> "2000"
               else -> "2300"
            }
            val baseDate = String.format("%04d%02d%02d",dateTime.year,dateTime.monthValue,dateTime.dayOfMonth)
            Log.e("현재 시간", dateTime.toLocalTime().toString())
            Log.e("오늘 날짜", baseDate)
            Log.e("오늘 날짜", baseTime)


            return BaseDateTime(baseDate,baseTime)
        }
    }
}
