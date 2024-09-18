package com.inik.imageextract.mvi

sealed class MviIntent {
    object LoadImage : MviIntent()
}