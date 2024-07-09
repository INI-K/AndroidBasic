package com.inik.myweather

import android.util.Log
import com.inik.myweather.databinding.ItemForecastBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLDecoder

object WeatherRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://apis.data.go.kr/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(WeatherService::class.java)

    fun getVillageForecase(longitude: Double,
                           latitude: Double,
                           successCallback:(List<Forecast>) -> Unit,
                           failureCallback:(Throwable) -> Unit
    ){
        val baseDateTime = BaseDateTime.getBaseDateTime()
        val converter = GeoPointConverter()
        val point = converter.convert(lat = latitude, lon = longitude)
        service.getVillageForcast(
            serviceKey = URLDecoder.decode("3EnISFLINP4kgXNSKb07e1HPRRn1MZEH%2B830uHfKiWAuUCvlpAvBibjGjYB0f8yKyjsN3PiR2l%2FJcJBo2PYHJg%3D%3D", "UTF-8"),
            baseDate = baseDateTime.baseDate,
            baseTime = baseDateTime.baseTime,
            nx = point.nx,
            ny = point.ny
        ).enqueue(object : Callback<WeatherEntity> {
            override fun onResponse(call: Call<WeatherEntity>, response: Response<WeatherEntity>) {

                val forecastDateTimeMap = mutableMapOf<String, Forecast>()
                val forecastList =
                    response.body()?.response?.body?.items?.forecastEntities.orEmpty()

                for (forecast in forecastList) {
                    if (forecastDateTimeMap["${forecast.forecastDate} / ${forecast.forecastTime}"] == null) {
                        forecastDateTimeMap["${forecast.forecastDate} / ${forecast.forecastTime}"] =
                            Forecast(
                                forecastDate = forecast.forecastDate,
                                forecastTime = forecast.forecastTime
                            )
                    }

                    forecastDateTimeMap["${forecast.forecastDate} / ${forecast.forecastTime}"]?.apply {

                        when (forecast.category) {
                            Category.POP -> precipitation = forecast.forecastValue.toInt()
                            Category.PTY -> transformRainType(forecast)
                            Category.SKY -> sky = transformSky(forecast)
                            Category.TMP -> temperature = forecast.forecastValue.toDouble()
                            else -> {

                            }
                        }
                    }
                }
                val list = forecastDateTimeMap.values.toMutableList()
                list.sortWith{f1,f2 ->
                    val f1DateTime = "${f1.forecastDate}${f1.forecastTime}"
                    val f2DateTime = "${f2.forecastDate}${f2.forecastTime}"

                    return@sortWith f1DateTime.compareTo(f2DateTime)
                }
                if(list.isEmpty()){
                    failureCallback(NullPointerException())
                }else{
                    successCallback(list)
                }
            }

            override fun onFailure(call: Call<WeatherEntity>, t: Throwable) {
                Log.e("날씨 실패", call.request().toString())
                t.printStackTrace()
            }
        })
    }
    private fun transformSky(forecastEntity: ForecastEntity): String {
        return when (forecastEntity.forecastValue.toInt()) {
            0 -> "없음"
            1 -> "비"
            2 -> "비/눈"
            3 -> "눈"
            4 -> "소나기"
            else -> ""
        }
    }

    private fun transformRainType(forecastEntity: ForecastEntity): String {
        return when (forecastEntity.forecastValue.toInt()) {
            1 -> "맑음"
            3 -> "구름 많음"
            4 -> "흐림"
            else -> ""
        }
    }
}