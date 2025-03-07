package com.inik.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.inik.domain.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(useCase: MainUseCase) : ViewModel() {
    private val _columnCount = MutableStateFlow(DEFAULT_COLUM_COUNT)
    val columnCount: StateFlow<Int> = _columnCount

    val modelList = useCase.getModelList()

    fun openSearchForm() {
        println("openSearchForm")
    }

    fun updateColumnCount(count : Int){
        viewModelScope.launch {
            _columnCount.emit(count)
        }
    }

    companion object {
        private const val DEFAULT_COLUM_COUNT = 2
    }
}