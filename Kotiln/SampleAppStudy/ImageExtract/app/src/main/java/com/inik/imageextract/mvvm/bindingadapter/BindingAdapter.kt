package com.inik.imageextract.mvvm.bindingadapter

import android.graphics.Color
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.inik.imageextract.mvvm.model.Image

@BindingAdapter("image")
fun ImageView.setImage(image: Image?){
    if(image == null){
        return
    }

    setBackgroundColor(Color.parseColor(image.color))
    load(image.url){
        crossfade(300)
    }
}