package com.inik.facecamera

import android.graphics.PointF
import android.graphics.RectF
import android.os.Bundle
import android.util.SizeF
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.transition.TransitionManager
import com.inik.face_recognition.Camera
import com.inik.face_recognition.recognition.FaceAnalyzerListener
import com.inik.facecamera.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), FaceAnalyzerListener {
    private lateinit var binding: ActivityMainBinding
    private val camera = Camera(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setProgressText("시작하기를 눌러주세요")
        camera.initCamera(binding.cameraLayout, this@MainActivity)

        binding.startDetectBtn.setOnClickListener {
            it.isVisible = false
            binding.faceOverlayView.reset()
            camera.startFaceDetect()
            setProgressText("얼굴을 보여주세요")
        }

    }

    override fun detect() {

    }

    override fun stopDetect() {
        camera.stopFaceDetect()
        reset()
    }

    override fun notDetect() {
        binding.faceOverlayView.reset()
    }

    override fun detectProgress(progess: Float, message: String) {
        setProgressText(message)
        binding.faceOverlayView.setProgress(progess)
    }

    override fun faceSize(rectF: RectF, sizeF: SizeF, pointF: PointF) {
        binding.faceOverlayView.setSize(rectF,sizeF, pointF)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        camera.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun reset() {
        binding.startDetectBtn.isVisible = true
    }

    private fun setProgressText(text: String) {
        TransitionManager.beginDelayedTransition(binding.root)
        binding.progressTextView.text = text
    }
}