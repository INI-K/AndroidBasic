package com.inik.todayhome.ui.article

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WriteArticleViewModel: ViewModel() {

    private var _selectedUri = MutableLiveData<Uri?>()
    var seletedUri: LiveData<Uri?> = _selectedUri

    fun updateSeletedUri(uri: Uri?){
        _selectedUri.value = uri
    }

}