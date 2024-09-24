package com.inik.blindclone.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inik.blindclone.domain.model.Content
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.http.Path

@HiltViewModel
class InputViewModel : ViewModel() {
    private val _doneEvent = MutableLiveData<Pair<Boolean, String>>()

    val doneEvent: LiveData<Pair<Boolean, String>> = _doneEvent

    var category = MutableLiveData("")
    var title = MutableLiveData("")
    var contet = MutableLiveData("")
    private var item: Content? = null

    fun initData(item: Content) {
        this.item = item
        category.value = item.category
        title.value = item.title
        contet.value = item.content
    }

    fun insertData() {
        val categoryValue = category.value
        val titleValue = title.value
        val contentValue = contet.value
        if (categoryValue.isNullOrBlank() ||
            titleValue.isNullOrBlank() ||
            contentValue.isNullOrBlank()
        ) {
            _doneEvent.value = Pair(false,"모든 항목을 입력하셔야 합니다.")
        }
    }

    //todo api
}