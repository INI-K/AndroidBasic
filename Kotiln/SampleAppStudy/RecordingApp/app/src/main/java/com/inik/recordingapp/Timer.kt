package com.inik.recordingapp

import android.os.Handler
import android.os.Looper
import kotlin.time.Duration

class Timer(listener: OnTimerTickListener) {
    private var duration: Long = 0L
    private val handler = Handler(Looper.getMainLooper())
    private val runnable: Runnable = object: Runnable{
        override fun run() {
            duration += 40L
            handler.postDelayed(this, 40L)
            listener.onTick(duration)
        }
    }

    fun start(){
        handler.postDelayed(runnable,40L)
    }
    fun stop(){
        duration = 0L
        handler.removeCallbacks(runnable)
    }
}

interface OnTimerTickListener{
    fun onTick(duration: Long)
}