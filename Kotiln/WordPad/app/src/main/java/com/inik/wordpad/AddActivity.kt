package com.inik.wordpad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.children
import androidx.core.widget.addTextChangedListener
import com.google.android.material.chip.Chip
import com.inik.wordpad.databinding.ActivityAddActiviryBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddActiviryBinding
    private var originWord : Word? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddActiviryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        binding.addBtn.setOnClickListener {
            if(originWord == null) add() else edit()
        }
    }

    private fun initViews() {
        val types = listOf("명사", "동사", "대명사", "형용사", "부사", "감탄사", "전치사", "접속사")
        binding.typeChipGroup.apply {
            types.forEach { text ->
                addView(createChip(text))
            }
        }

        binding.textInputEditText.addTextChangedListener {
            it?.let { text->
               binding.textInputEditText.error =
                   when(text.length){
                    0 -> "값을 입력해주세요"
                    1 -> "2글자 이상 입력해주세요"
                    else -> null
                }
            }
        }

        originWord = intent.getParcelableExtra("originWord")
        originWord?.let { word ->
            binding.textInputEditText.setText(word.text)
            binding.meanTextInputEditText.setText(word.text)
            val selectChip = binding.typeChipGroup.children.firstOrNull {(it as Chip).text == word.type} as? Chip
            selectChip?.isCheckable = true
        }
    }

    private fun createChip(text: String): Chip {
        return Chip(this).apply {
            setText(text)
            isCheckable = true
            isClickable = true
        }
    }

    private fun add() {
        val text = binding.textInputEditText.text.toString()
        val mean = binding.meanTextInputEditText.text.toString()
        val type = findViewById<Chip>(binding.typeChipGroup.checkedChipId).text.toString()
        val word = Word(text, mean, type)

        Thread {
            AppDatabase.getInstance(this)?.wordDao()?.insert(word)
            runOnUiThread {
                Toast.makeText(this, "저장을 완료했습니다.", Toast.LENGTH_LONG).show()
            }
            val intent = Intent().putExtra("isUpdated", true)
            setResult(RESULT_OK, intent)
            finish()
        }.start()
    }

    private fun edit(){
        val text = binding.textInputEditText.text.toString()
        val mean = binding.meanTextInputEditText.text.toString()
        val type = findViewById<Chip>(binding.typeChipGroup.checkedChipId).text.toString()
        val editWord = originWord?.copy(text = text, mean = mean, type = type)
        Thread{
            editWord?.let { word ->
                AppDatabase.getInstance(this)?.wordDao()?.update(editWord)
                val intent = Intent().putExtra("editWord", editWord)
                setResult(RESULT_OK,intent)
                runOnUiThread { Toast.makeText(this,"수정을 완료 했습니다.", Toast.LENGTH_LONG).show() }
                finish()
            }
        }.start()
    }
}