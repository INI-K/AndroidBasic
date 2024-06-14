package com.inik.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.inik.stopwatch.databinding.ActivityMainBinding
import com.inik.stopwatch.databinding.DialogCountdownSettingBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var countdownSecond = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dexOutputDir: File = codeCacheDir
        dexOutputDir.setReadOnly()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.countdownTextView.setOnClickListener{
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
    }


    private fun start() {

    }

    private fun stop() {
        binding.startBtn.isVisible = true;
        binding.stopBtn.isVisible = true;
        binding.pauseBtn.isVisible = false;
        binding.lapBtn.isVisible = false;
    }

    private fun pause() {

    }

    private fun lap() {

    }

    private fun showCountDownSettingDialog(){
        AlertDialog.Builder(this).apply {
            val dialogBinding = DialogCountdownSettingBinding.inflate(layoutInflater)
            with(dialogBinding.countdownSecondPicker){
                maxValue = 20
                minValue = 0
                value = countdownSecond
            }
            setTitle("카운드다운 설정")
            setView(dialogBinding.root)
            setPositiveButton("확인"){_, _ ->
                countdownSecond = dialogBinding.countdownSecondPicker.value
                binding.countdownTextView.text = String.format("%02d", countdownSecond)
            }
            setNegativeButton("취소",null)

        }.show()
    }

    private fun showAlertDialog(){
        AlertDialog.Builder(this).apply {
            setMessage("종료하시겠습니까?")
            setPositiveButton("네"){_, id ->
                stop()
            }
            setNegativeButton("아니요",null)
        }.show()
    }
}