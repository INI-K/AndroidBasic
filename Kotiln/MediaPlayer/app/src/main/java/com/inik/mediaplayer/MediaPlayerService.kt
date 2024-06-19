package com.inik.mediaplayer

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.drawable.Icon
import android.media.MediaPlayer
import android.os.IBinder

class MediaPlayerService : Service() {
    private  var mediaPlayer: MediaPlayer? = null
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    @SuppressLint("ForegroundServiceType")
    override fun onCreate() {
        super.onCreate()
        creatNotficaitonChannel()

        val playIcon = Icon.createWithResource(baseContext,R.drawable.baseline_play_arrow_24)
        val pauseIcon = Icon.createWithResource(baseContext,R.drawable.baseline_pause_24)
        val stopIcon = Icon.createWithResource(baseContext,R.drawable.baseline_stop_24)

        val pausePandingIntent = PendingIntent.getService(
            baseContext,
            0,
            Intent(baseContext,MediaPlayerService::class.java).apply { action = MEDIA_PLAYER_PAUSE },
            PendingIntent.FLAG_IMMUTABLE
        )
        val playPandingIntent = PendingIntent.getService(
            baseContext,
            0,
            Intent(baseContext,MediaPlayerService::class.java).apply { action = MEDIA_PLAYER_PLAY },
            PendingIntent.FLAG_IMMUTABLE
        )
        val stopPandingIntent = PendingIntent.getService(
            baseContext,
            0,
            Intent(baseContext,MediaPlayerService::class.java).apply { action = MEDIA_PLAYER_STOP },
            PendingIntent.FLAG_IMMUTABLE
        )

        val mainPendingIntent = PendingIntent.getActivity(
            baseContext,
            0,
            Intent(baseContext,MainActivity::class.java)
                .apply {
                    setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                },
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = Notification.Builder(baseContext, CHANNEL_ID)
            .setStyle(
                Notification.MediaStyle()
                    .setShowActionsInCompactView(0,1,2)
            )
            .setVisibility(Notification.VISIBILITY_PUBLIC)
            .setSmallIcon(R.drawable.baseline_star_24)
            .addAction(
                Notification.Action.Builder(
                    pauseIcon,
                    "Pause",
                    pausePandingIntent
                ).build()
            )
            .addAction(
                Notification.Action.Builder(
                    playIcon,
                    "Play",
                    playPandingIntent
                ).build()
            )
            .addAction(
                Notification.Action.Builder(
                    stopIcon,
                    "Stop",
                    stopPandingIntent
                ).build()
            )
            .setContentIntent(mainPendingIntent)
            .setContentTitle("음악재생")
            .setContentText("음원이 재생 중 입니다")
            .build()

        startForeground(100, notification)
    }

    private fun creatNotficaitonChannel(){
        val channel = NotificationChannel(CHANNEL_ID, "MEDIA_PLAY_CHANNEL",NotificationManager.IMPORTANCE_DEFAULT)

        val notificationManager = baseContext.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action){
            MEDIA_PLAYER_PLAY ->{
                if(mediaPlayer == null){
                    mediaPlayer = MediaPlayer.create(baseContext,R.raw.music)
                }
                mediaPlayer?.start()
            }
            MEDIA_PLAYER_PAUSE ->{
                mediaPlayer?.pause()
            }
            MEDIA_PLAYER_STOP ->{
                mediaPlayer?.stop()
                mediaPlayer?.release()
                mediaPlayer = null
                stopSelf()
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        mediaPlayer?.apply {
            stop()
            release()
        }
        mediaPlayer == null
        super.onDestroy()
    }
}