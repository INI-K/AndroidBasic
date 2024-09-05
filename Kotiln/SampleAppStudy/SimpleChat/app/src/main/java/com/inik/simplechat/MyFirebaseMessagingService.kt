package com.inik.simplechat

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val name = "SimpleChat"
        val descriptionText = "채팅 알림입니다."
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(getString(R.string.default_notification_channel_id), name, importance).apply { description = descriptionText
        }
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

       val body = message.notification?.body ?: ""
        val title = message.notification?.title ?: ""
       val notificationBuilder = NotificationCompat.Builder(applicationContext,getString(R.string.default_notification_channel_id))
            .setSmallIcon(R.drawable.baseline_chat_24)
            .setContentTitle("SimpleChat")
            .setContentText(title + ":  " + body)

        notificationManager.notify(0, notificationBuilder.build())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

}