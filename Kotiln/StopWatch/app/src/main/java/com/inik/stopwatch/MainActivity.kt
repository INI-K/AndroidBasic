package com.inik.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import com.inik.stopwatch.databinding.ActivityMainBinding
import com.inik.stopwatch.databinding.DialogCountdownSettingBinding
import java.io.File
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var countdownSecond = 10
    private var currentCountdownDeciSecond = countdownSecond * 10
    private var currentDecisecond = 0
    private var timer: Timer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dexOutputDir: File = codeCacheDir
        dexOutputDir.setReadOnly()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.countdownTextView.setOnClickListener {
            showCountDownSettingDialog()
        }

        binding.startBtn.setOnClickListener {
            start()
            binding.startBtn.isVisible = false;
            binding.stopBtn.isVisible = false;
            binding.pauseBtn.isVisible = true;
            binding.lapBtn.isVisible = true;
        }

        binding.stopBtn.setOnClickListener {
            showAlertDialog()
        }

        binding.pauseBtn.setOnClickListener {
            pause()
            binding.startBtn.isVisible = true;
            binding.stopBtn.isVisible = true;
            binding.pauseBtn.isVisible = false;
            binding.lapBtn.isVisible = false;
        }

        binding.lapBtn.setOnClickListener {
            lap()
        }

        initCountdownView()
    }

    private fun initCountdownView() {
        binding.countdownTextView.text = String.format("%02d", countdownSecond)
        binding.countdownProgressBar.progress = 100
    }

    private fun start() {
        timer = timer(initialDelay = 0, period = 100) {
            if (currentCountdownDeciSecond == 0) {
                currentDecisecond += 1

                val minute = currentDecisecond.div(10) / 60
                val second = currentDecisecond.div(10) % 60
                val deciseconds = currentDecisecond % 10

                runOnUiThread {
                    binding.timeTextView.text =
                        String.format("%02d:%02d", minute, second)
                    binding.tickTextView.text = deciseconds.toString()

                    binding.countdownGroup.isVisible = false
                }
            } else {
                currentCountdownDeciSecond -= 1
                val second = currentCountdownDeciSecond / 10
                val progress = (currentCountdownDeciSecond / (countdownSecond * 10f)) * 100

                binding.root.post {
                    binding.countdownTextView.text = String.format("%02d", second)
                    binding.countdownProgressBar.progress = progress.toInt()
                }
            }
        }
    }

    private fun stop() {
        binding.startBtn.isVisible = true;
        binding.stopBtn.isVisible = true;
        binding.pauseBtn.isVisible = false;
        binding.lapBtn.isVisible = false;

        currentDecisecond = 0
        binding.timeTextView.text = "00:00"
        binding.tickTextView.text = "0"

        binding.countdownGroup.isVisible = true
        initCountdownView()
    }

    private fun pause() {
        timer?.cancel()
        timer = null
    }

    private fun lap() {
        val container = binding.lapContainerLinewarLayout
        val lapTextView = TextView(this).apply {
            textSize = 20f
            gravity = Gravity.CENTER
            val minute = currentDecisecond.div(10) / 60
            val second = currentDecisecond.div(10) % 60
            val deciSecond = currentDecisecond % 10
            text = container.childCount.inc().toString()+". " + String.format(
                "%02d:%02d %01d",
                minute,
                second,
                deciSecond
            )
            setPadding(30)
        }.let {labTextView ->
            container.addView(labTextView,0)
        }
    }

    private fun showCountDownSettingDialog() {
        AlertDialog.Builder(this).apply {
            val dialogBinding = DialogCountdownSettingBinding.inflate(layoutInflater)
            with(dialogBinding.countdownSecondPicker) {
                maxValue = 20
                minValue = 0
                value = countdownSecond
            }
            setTitle("카운드다운 설정")
            setView(dialogBinding.root)
            setPositiveButton("확인") { _, _ ->
                countdownSecond = dialogBinding.countdownSecondPicker.value
                currentCountdownDeciSecond = countdownSecond * 10
                binding.countdownTextView.text = String.format("%02d", countdownSecond)
            }
            setNegativeButton("취소", null)

        }.show()
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(this).apply {
            setMessage("종료하시겠습니까?")
            setPositiveButton("네") { _, id ->
                stop()
            }
            setNegativeButton("아니요", null)
        }.show()
    }
}