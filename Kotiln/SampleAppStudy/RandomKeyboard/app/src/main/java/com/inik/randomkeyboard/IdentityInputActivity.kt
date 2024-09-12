package com.inik.randomkeyboard

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.inik.randomkeyboard.databinding.ActivityIndentityInputBinding
import com.inik.randomkeyboard.util.ViewUtil.hideKeyboard
import com.inik.randomkeyboard.util.ViewUtil.setOnEditorActionListener
import com.inik.randomkeyboard.util.ViewUtil.showKeyboard
import com.inik.randomkeyboard.util.ViewUtil.showKeyboardDelay


class IdentityInputActivity: AppCompatActivity() {
    private lateinit var binding: ActivityIndentityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIndentityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
        initView()
        binding.nameEdit.showKeyboardDelay()

    }

    private fun initView(){
        with(binding){
            nameEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT){
                birthdayLayout.isVisible = true
                birthdayEdit.showKeyboard()
            }
            birthdayEdit.doAfterTextChanged {
                if(birthdayEdit.length() > 7){
                    genderLayout.isVisible = true
                    birthdayEdit.hideKeyboard()
                }
            }
            genderChipGroup.setOnCheckedStateChangeListener { chipGroup, checkedIds ->
                if(!telecomLayout.isVisible){
                    telecomLayout.isVisible = true
                }
            }
            telecomChipGroup.setOnCheckedStateChangeListener { chipGroup, checkedIds ->
                if(!phoneLayout.isVisible){
                    phoneLayout.isVisible=true
                    phoneEdit.showKeyboard()
                }
            }
            phoneEdit.doAfterTextChanged {
                if(phoneEdit.length() > 10){
                    confirmButton.isVisible=true
                    phoneEdit.hideKeyboard()
                }
            }
            phoneEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT){
                if(phoneEdit.length() > 9){
                    confirmButton.isVisible = true
                    phoneEdit.hideKeyboard()
                }
            }
        }
    }
}