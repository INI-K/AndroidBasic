package com.inik.mediaplayer

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.inik.mediaplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playBtn.setOnClickListener { mediaPlayerPlay() }
        binding.pauseBtn.setOnClickListener { mediaPlayerPause() }
        binding.stopBtn.setOnClickListener { mediaPlayerStop() }
    }

    fun mediaPlayerPlay(){
        val intent = Intent(this, MediaPlayerService::class.java)
            .apply {
                action = MEDIA_PLAYER_PLAY
            }
        startService(intent)
//        if(mediaPlayer == null){
//            mediaPlayer = MediaPlayer.create(this,R.raw.music).apply {
//                isLooping = true
//            }
//        }
//        mediaPlayer?.start()
    }
    fun mediaPlayerPause(){
        val intent = Intent(this, MediaPlayerService::class.java)
            .apply {
                action = MEDIA_PLAYER_PAUSE
            }
        startService(intent)
    }
    fun mediaPlayerStop(){
        val intent = Intent(this, MediaPlayerService::class.java)
            .apply {
                action = MEDIA_PLAYER_STOP
            }
        startService(intent)
    }
}