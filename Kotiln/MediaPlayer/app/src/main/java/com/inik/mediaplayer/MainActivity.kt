package com.inik.mediaplayer

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.inik.mediaplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playBtn.setOnClickListener { mediaPlayerPlay() }
        binding.pauseBtn.setOnClickListener { mediaPlayerPause() }
        binding.stopBtn.setOnClickListener { mediaPlayerStop() }
    }

    fun mediaPlayerPlay(){
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(this,R.raw.music).apply {
                isLooping = true
            }
        }
        mediaPlayer?.start()
    }
    fun mediaPlayerPause(){
        mediaPlayer?.pause()
    }
    fun mediaPlayerStop(){
        mediaPlayer?.stop()
    }
}