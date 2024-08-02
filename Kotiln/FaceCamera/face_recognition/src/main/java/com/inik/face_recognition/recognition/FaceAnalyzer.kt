package com.inik.face_recognition.recognition

import android.graphics.PointF
import android.graphics.RectF
import android.media.FaceDetector
import android.media.Image
import android.util.SizeF
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import androidx.lifecycle.Lifecycle
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import kotlin.math.abs

internal class FaceAnalyzer(
    lifecycle: Lifecycle,
    private val preview: PreviewView,
    private val listener: FaceAnalyzerListener?
) : ImageAnalysis.Analyzer {
    private var widthScaleFactor = 1F
    private var heightScaleFactor = 1F

    private var preCenterX = 0F
    private var preCenterY = 0F
    private var preWidth = 0F
    private var preHeight = 0F

    private val options = FaceDetectorOptions.Builder()
        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
        .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
        .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
        .setMinFaceSize(0.4F)
        .build()

    private val detector = FaceDetection.getClient(options)

    private var detectorStatus = FaceAnalyzerStatus.UnDetect

    private val successListener = OnSuccessListener<List<Face>> { faces ->
        val face = faces.firstOrNull()
        if (face != null) {
            if (detectorStatus == FaceAnalyzerStatus.UnDetect) {
                detectorStatus = FaceAnalyzerStatus.Detect
                listener?.detect()
                listener?.detectProgress(25F, "얼굴을 인식했습니다. \n왼쪽 눈만 깜빡여 주세요.")
            } else if (detectorStatus == FaceAnalyzerStatus.Detect
                && (face.leftEyeOpenProbability ?: 0F) > EYE_SUCCESS_VALUE
                && (face.rightEyeOpenProbability ?: 0F) < EYE_SUCCESS_VALUE
            ) {
                detectorStatus = FaceAnalyzerStatus.LeftWink
                listener?.detectProgress(50F, "오늘쪽 눈만 깜빡여주세요")
            } else if (detectorStatus == FaceAnalyzerStatus.LeftWink
                && (face.leftEyeOpenProbability ?: 0F) < EYE_SUCCESS_VALUE
                && (face.rightEyeOpenProbability ?: 0F) > EYE_SUCCESS_VALUE
            ) {
                detectorStatus = FaceAnalyzerStatus.RightWink
                listener?.detectProgress(75F, "활짝 웃어보세요")
            } else if (detectorStatus == FaceAnalyzerStatus.RightWink
                && (face.smilingProbability ?: 0F) > SMILE_SUCCESS_VALUE
            ) {
                detectorStatus = FaceAnalyzerStatus.Smile
                listener?.detectProgress(100F, "얼굴 인식이 완료되었습니다.")
                listener?.stopDetect()
                detector.close()
            }
            calDetectSize(face)
        } else if (detectorStatus != FaceAnalyzerStatus.UnDetect
            && detectorStatus != FaceAnalyzerStatus.Smile
        ) {
            detectorStatus = FaceAnalyzerStatus.UnDetect
            listener?.notDetect()
            listener?.detectProgress(0F, "얼굴을 하지 못했습니다. \n처음으로 돌아갑니다.")
        }
    }

    private val failureListener = OnFailureListener { e ->
        detectorStatus = FaceAnalyzerStatus.UnDetect
    }

    init {
        lifecycle.addObserver(detector)
    }

    override fun analyze(image: ImageProxy) {
        widthScaleFactor = preview.width.toFloat() / image.height
        heightScaleFactor = preview.height.toFloat() / image.width
        detectFaces(image)
    }


    private fun detectFaces(imageProxy: ImageProxy) {
        val image = InputImage.fromMediaImage(
            imageProxy.image as Image,
            imageProxy.imageInfo.rotationDegrees
        )
        detector.process(image)
            .addOnSuccessListener(successListener)
            .addOnFailureListener(failureListener)
            .addOnCompleteListener {
                imageProxy.close()
            }
    }

    private fun calDetectSize(face: Face) {
        val rect = face.boundingBox
        val boxWidth = rect.right - rect.left
        val boxHeigt = rect.bottom - rect.top

        val left = rect.right.traslateX() - (boxWidth / 2)
        val top = rect.top.traslateY() - (boxHeigt / 2)
        val right = rect.left.traslateX() + (boxWidth / 2)
        val bottom = rect.bottom.traslateY()

        val width = right - left
        val height = bottom - top
        val centerX = left + width / 2
        val centerY = top + height / 2

        if (abs(preCenterX - centerX) > PIVOT_OFFSET
            || abs(preCenterY - centerY) > PIVOT_OFFSET
            || abs(preWidth - width) > SIZE_OFFSET
            || abs(preHeight -height) > SIZE_OFFSET) {
            listener?.faceSize(
                RectF(left, top, right, bottom),
                SizeF(width, height),
                PointF(centerX,centerY)
            )
            preCenterX = centerX
            preCenterY = centerY
            preWidth = width
            preHeight = height
        }
    }

    private fun Int.traslateX() = preview.width - (toFloat() * widthScaleFactor)
    private fun Int.traslateY() = toFloat() * heightScaleFactor

    companion object {
        private const val EYE_SUCCESS_VALUE = 0.1F
        private const val SMILE_SUCCESS_VALUE = 0.8F

        private const val PIVOT_OFFSET = 15
        private const val SIZE_OFFSET = 30

    }
}