package com.inik.recordingapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.time.Duration

class WavaformView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
): View(context, attrs, defStyleAttr) {

    private val ampList = mutableListOf<Float>()
    private val rectList = mutableListOf<RectF>()
    val rectWidth = 10f
    private var tick = 0

    private val redPaint = Paint().apply {
        color = Color.RED
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for(rectF in rectList){
            canvas?.drawRect(rectF, redPaint)
        }
    }

    fun addAmplitude(maxAmplitude: Float){
        val height = this.height
        val amplitude = maxAmplitude / Short.MAX_VALUE * height * 0.8f

        ampList.add(amplitude)
        rectList.clear()

        val maxRect = (this.width / rectWidth).toInt()

        val amps = ampList.takeLast(maxRect)

        for ((i , amp) in amps.withIndex()){
            val rectF = RectF()
            rectF.top = (this.height / 2) - amp / 2 - 3f
            rectF.bottom = rectF.top + amp + 3f
            rectF.left = i * rectWidth
            rectF.right =rectF.left + rectWidth - 5f

            rectList.add(rectF)
        }

        invalidate()
    }

    fun replayAmplitude(duration: Int){
        rectList.clear()

        val maxRect = (this.width/rectWidth).toInt()
        val amps =ampList.take(tick).takeLast(maxRect)

        for ((i , amp) in amps.withIndex()){
            val rectF = RectF()
            rectF.top = (this.height / 2) - amp / 2 - 3f
            rectF.bottom = rectF.top + amp + 3f
            rectF.left = i * rectWidth
            rectF.right =rectF.left + rectWidth - 5f

            rectList.add(rectF)
        }

        tick++

        invalidate()
    }
    fun clearData(){
        ampList.clear()
    }
    fun clearWave(){
        rectList.clear()
        tick = 0
        invalidate()
    }
}