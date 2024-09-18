package com.inik.imageextract.mvi

import com.inik.imageextract.mvi.model.Image

sealed class MviState {
    object Idle: MviState()
    object Loading: MviState()
    data class LoadedImage(val image: Image, val count: Int): MviState()
}