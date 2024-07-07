package com.inik.myweather

import android.health.connect.datatypes.units.Temperature

data class Forecast(
    val forecastDate: String,
    val forecastTime: String,

    var temperature: Double = 0.0,
    var sky: String = "",
    var precipitation: Int = 0,
    var precipitaitonType: String = ""
){
    val weather:String
        get(){
            return if(precipitaitonType == ""|| precipitaitonType == "없음"){
                sky
            }else{
                precipitaitonType
            }
        }
}