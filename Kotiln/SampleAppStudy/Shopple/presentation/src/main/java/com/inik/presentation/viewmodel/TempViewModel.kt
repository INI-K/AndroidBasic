package com.inik.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.inik.domain.model.TempModel
import com.inik.domain.usecase.TempUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TempViewModel @Inject constructor(private val useCase: TempUseCase) : ViewModel() {

    fun getTempModel() : TempModel {
        Log.e("111","들어옴?")
        return useCase.getTempModel()
    }
}