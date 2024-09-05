package com.inik.myweather

import android.app.ForegroundServiceStartNotAllowedException
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.content.ContextCompat

class WeatherWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        appWidgetIds.forEach { appWidgetId ->

            val pendingIntent: PendingIntent = PendingIntent.getService(
                context,
                0,
                Intent(context, UpdateWeatherService::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

//            val pendingIntent: PendingIntent =
//                Intent(context, UpdateWeatherService::class.java).let { intent ->
//                    PendingIntent.getActivity(context,1,intent,PendingIntent.FLAG_IMMUTABLE)
//                }

            val views: RemoteViews = RemoteViews(
                context.packageName,
                R.layout.widget_weather
            ).apply {
                setOnClickPendingIntent(R.id.temperatureTextView, pendingIntent)
            }

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
        val serviceIntent = Intent(context, UpdateWeatherService::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            try {
                Log.e("타줘", "제발요")
                ContextCompat.startForegroundService(context, serviceIntent)
            } catch (e: ForegroundServiceStartNotAllowedException) {
                e.printStackTrace()
            }
        } else {
            ContextCompat.startForegroundService(context, serviceIntent)
        }
    }
}