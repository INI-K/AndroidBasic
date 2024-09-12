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


class IdentityInputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIndentityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIndentityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
        initView()
        binding.nameEdit.showKeyboardDelay()

    }

    private fun initView() {
        with(binding) {
            nameEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT) {
                if(vaildName()){
                    nameLayout.error = null
                    if(phoneLayout.isVisible){
                        confirmButton.isVisible = true
                    }else{
                        birthdayLayout.isVisible = true
                        birthdayEdit.showKeyboard()
                    }
                }else{
                    confirmButton.isVisible = false
                    nameLayout.error = "1자 이상의 한글을 입력해주세요"
                }
            }
            birthdayEdit.doAfterTextChanged {
                if (birthdayEdit.length() > 7) {
                    if(vaildBirthday()){
                        birthdayLayout.error = null
                        if(phoneLayout.isVisible){
                            confirmButton.isVisible = true
                        }else{
                            genderLayout.isVisible = true
                            birthdayEdit.hideKeyboard()
                        }
                    }else{
                        confirmButton.isVisible = false
                        birthdayLayout.error = "생년월일 형식이 다릅니다."
                    }
                }
            }
            birthdayEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE){
                val isVaild = vaildBirthday() && birthdayEdit.length() > 7
                if(isVaild){
                    confirmButton.isVisible = phoneLayout.isVisible
                    birthdayLayout.error =null
                }else{
                    birthdayLayout.error = "생년월일 형식이 다릅니다."
                }
                birthdayEdit.hideKeyboard()
            }
            genderChipGroup.setOnCheckedStateChangeListener { chipGroup, checkedIds ->
                if (!telecomLayout.isVisible) {
                    telecomLayout.isVisible = true
                }
            }
            telecomChipGroup.setOnCheckedStateChangeListener { chipGroup, checkedIds ->
                if (!phoneLayout.isVisible) {
                    phoneLayout.isVisible = true
                    phoneEdit.showKeyboard()
                }
            }
            phoneEdit.doAfterTextChanged {
                if (phoneEdit.length() > 10) {
                    if(vaildPhone()){
                        phoneLayout.error = null
                        confirmButton.isVisible = true
                        phoneEdit.hideKeyboard()
                    }else{
                        confirmButton.isVisible = false
                        phoneLayout.error = "전화 번호 형식이 다릅니다."

                    }

                }
            }
            phoneEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT) {
                    confirmButton.isVisible = phoneEdit.length() > 9 && vaildPhone()
                    phoneEdit.hideKeyboard()

            }
        }
    }
    fun onClickDone(){
        if(!vaildName()){
            binding.nameLayout.error = "1자이상의 한글을 입력해주세요."
            return
        }
        if(!vaildBirthday()){
            binding.birthdayLayout.error = "생년월일 형식이 다릅니다."
            return
        }
        if(!vaildPhone()){
            binding.phoneLayout.error = "전화 번호 형식이 다릅니다."
        }
    }

    private fun vaildName() = !binding.nameEdit.text.isNullOrBlank()
            && REGEX_NAME.toRegex().matches(binding.nameEdit.text!!)

    private fun vaildBirthday() = !binding.nameEdit.text.isNullOrBlank()
            && REGEX_BIRTHDAY.toRegex().matches(binding.birthdayEdit.text!!)

    private fun vaildPhone() = !binding.nameEdit.text.isNullOrBlank()
            && REGEX_PHONE.toRegex().matches(binding.phoneEdit.text!!)

    companion object {
        private const val REGEX_NAME = "^[가-힣]{2,}\$"
        private const val REGEX_BIRTHDAY =
            "^(19|20)[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1,2][0-9]|3[0,1])"
        private const val REGEX_PHONE = "^01([016789])([0-9]{3,4})([0-9]{4})\$"
    }
}