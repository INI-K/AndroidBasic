package com.inik.myweather

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.net.URLDecoder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var gson = GsonBuilder().setLenient().create()


        val retrofit = Retrofit.Builder()
            .baseUrl("http://apis.data.go.kr/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val service = retrofit.create(WeatherService::class.java)
        val baseDateTime = BaseDateTime.getBaseDateTime()
        val converter = GeoPointConverter()
        val point = converter.convert(lat = 37.5581, lon = 126.9083)

        Log.e("시간 확인(Date)",baseDateTime.baseDate)
        Log.e("시간 확인(Time)",baseDateTime.baseTime)

        service.getVillageForcast(
            serviceKey = URLDecoder.decode(getString(R.string.apiKey), "UTF-8"),
            baseDate = baseDateTime.baseDate,
            baseTime = baseDateTime.baseTime,
            nx = point.nx,
            ny = point.ny
        ).enqueue(object : Callback<WeatherEntity> {
            override fun onResponse(call: Call<WeatherEntity>, response: Response<WeatherEntity>) {

                Log.e("날씨 응답11", call.request().url().toString())
                Log.e(
                    "날씨 응답22",
                    response.body()?.response?.body?.items?.forecastEntities?.get(1)?.category.toString()
                )
                val forecastDateTimeMap = mutableMapOf<String, Forecast>()
                val forecastList =
                    response.body()?.response?.body?.items?.forecastEntities.orEmpty()
                for (forecast in forecastList) {
                    Log.e("날시 예측", forecast.toString())
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

                Log.e("예측 확인", forecastDateTimeMap.toString())
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