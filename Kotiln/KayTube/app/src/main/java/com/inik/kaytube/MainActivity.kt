package com.inik.kaytube

import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.inik.kaytube.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val videoList: VideoList by lazy {
        readData("videos.json", VideoList::class.java) ?: VideoList(emptyList())
    }
    private lateinit var videoAdapter: VideoAdapter
    private lateinit var playerVideoAdapter: PlayerVideoAdapter
    private var player: ExoPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initMotionLayout()
        initVideoRecyclerView()
        initPlayerRecyclerView()

        initControlBtn()
        binding.hideBtn.setOnClickListener {
            binding.motionLayout.transitionToState(R.id.hide)
            player?.pause()
        }

        videoAdapter.submitList(videoList.videos)

    }


    private fun initControlBtn() {
        binding.controlBtn.setOnClickListener {
            player?.let {
                if (it.isPlaying) {
                    it.pause()
                } else {
                    it.play()
                }
            }
        }
    }

    private fun initVideoRecyclerView() {
        videoAdapter = VideoAdapter(context = this, onClick = { videoItem ->

            binding.motionLayout.setTransition(R.id.expand, R.id.collapse)
            binding.motionLayout.transitionToState(R.id.expand)

            val list = listOf(videoItem) + videoList.videos.filter { it.id != videoItem.id }
            playerVideoAdapter.submitList(list)

            play(videoItem)
        })

        binding.videoListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = videoAdapter
        }

    }

    private fun initPlayerRecyclerView() {
        playerVideoAdapter = PlayerVideoAdapter(context = this) { videoItem ->
            play(videoItem)

            val list = listOf(videoItem) + videoList.videos.filter { it.id != videoItem.id }
            playerVideoAdapter.submitList(list)
        }

        binding.playerRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = playerVideoAdapter
        }
    }

    private fun initMotionLayout() {
        binding.motionLayout.targetView = binding.videoPlayerContainer
        binding.motionLayout.jumpToState(R.id.hide)

        binding.motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {

            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
                binding.playerView.useController = false
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                binding.playerView.useController = (currentId == R.id.expand)
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {

            }

        })
    }

    private fun initExoPlayer() {
        player = ExoPlayer.Builder(this).build()
            .also { exoPlayer ->
                binding.playerView.player = exoPlayer
                binding.playerView.useController = false
                exoPlayer.addListener(object : Player.Listener {
                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        super.onIsPlayingChanged(isPlaying)

                        if (isPlaying) {
                            binding.controlBtn.setImageResource(R.drawable.baseline_pause_24)
                        } else {
                            binding.controlBtn.setImageResource(R.drawable.baseline_play_arrow_24)
                        }
                    }
                })
            }
    }

    private fun play(videoItem: VideoItem) {
        player?.setMediaItem(MediaItem.fromUri(Uri.parse(videoItem.videoUrl)))
        player?.prepare()
        player?.play()

        binding.videoTitleTextView.text = videoItem.title
    }

    override fun onStop() {
        super.onStop()
        player?.pause()
    }

    override fun onStart() {
        super.onStart()
        if (player == null) {
            initExoPlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if (player == null) {
            initExoPlayer()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        player?.release()
    }
}