package com.inik.myweather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    //https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?&pageNo=1
    // &numOfRows=1000
    // &dataType=Json
    // &base_date=20240705
    // &base_time=0200
    // &nx=55&ny=127
    @GET("1360000/VilageFcstInfoService_2.0/getVilageFcst?pageNo=1&numOfRows=400&dataType=json")
    fun getVillageForcast(
        @Query("serviceKey") serviceKey: String,
        @Query("base_date") baseDate: String,
        @Query("base_time") baseTime: String,
        @Query("nx") nx: Int,
        @Query("ny") ny: Int
    ): Call<WeatherEntity>
}