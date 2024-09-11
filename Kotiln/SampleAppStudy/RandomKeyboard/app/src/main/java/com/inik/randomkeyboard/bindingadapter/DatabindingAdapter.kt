package com.inik.randomkeyboard.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.inik.randomkeyboard.R

@BindingAdapter(value = ["code_text", "code_index"])
fun ImageView.setPin(codeText: CharSequence?, index: Int){
    if(codeText != null){
        if(codeText.length > index){
            setImageResource(R.drawable.baseline_circle_balck_24)
        }else{
            setImageResource(R.drawable.baseline_circle_grey_24)
        }
    }
}