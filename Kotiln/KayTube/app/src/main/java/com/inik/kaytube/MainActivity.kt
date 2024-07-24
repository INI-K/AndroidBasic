package com.inik.kaytube

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.inik.kaytube.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var videoAdapter: VideoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        videoAdapter = VideoAdapter(context = this, onClick = {videoItem ->
            binding.motionLayout.transitionToEnd()
        })

        binding.motionLayout.jumpToState(R.id.collapse)

        binding.videoListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = videoAdapter
        }

        val videoList = readData("videos.json",VideoList::class.java) ?: VideoList(emptyList())
        videoAdapter.submitList(videoList.videos)

    }
}