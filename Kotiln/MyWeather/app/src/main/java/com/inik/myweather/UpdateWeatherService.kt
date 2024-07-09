package com.inik.myweather

import android.Manifest
import android.app.PendingIntent
import android.app.Service
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices


class UpdateWeatherService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        //notifivation channer
        //foreground service 

        val appWidgetManager: AppWidgetManager = AppWidgetManager.getInstance(this)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //todo 위젯을 권한없음 상태로 표시하고, 클릭했을때 권한 팝업을 얻을 수 있도록 조정

            return super.onStartCommand(intent, flags, startId)
        }
        LocationServices.getFusedLocationProviderClient(this).lastLocation.addOnSuccessListener {

            WeatherRepository.getVillageForecase(
                longitude = it.longitude,
                latitude = it.latitude,
                successCallback = { forecastList ->

                    val currentForecast = forecastList.first()
                    val pendingServiceIntent: PendingIntent = Intent(this,UpdateWeatherService::class.java).let {
                        PendingIntent.getService(this,1,it,PendingIntent.FLAG_IMMUTABLE)
                    }

                    RemoteViews(packageName,R.layout.widget_weather).apply{
                        setTextViewText(
                            R.id.temperatureTextView,
                            getString(R.string.temperature_text,currentForecast.temperature)
                        )
                        setTextViewText(
                            R.id.weatherTextView,
                            currentForecast.weather
                        )
                        setOnClickPendingIntent(R.id.temperatureTextView,pendingServiceIntent)
                    }.also {remoteViews ->
                        val appWidgetName = ComponentName(this,WeatherWidgetProvider::class.java)
                        appWidgetManager.updateAppWidget(
                            appWidgetName,
                            remoteViews
                        )
                    }
                    stopSelf()
                },
                failureCallback = {
                    //todo 위젯에러상태표시


                    stopSelf()
                }
            )
        }


        return super.onStartCommand(intent, flags, startId)
    }
}