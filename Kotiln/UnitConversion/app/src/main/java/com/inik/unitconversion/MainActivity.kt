package com.inik.unitconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.inik.unitconversion.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var inputNumber: Int = 0
    var cmToM = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val outputTextView = binding.outputTextView
        val outputUnitTextView = binding.outputUnitTextView
        val inputEditTextView = binding.inputEditText
        val inputUnitTextView = binding.inputUnitTextView
        val swapImageBtn = binding.swapBtn

        inputEditTextView.addTextChangedListener { text ->
            inputNumber = if (text.isNullOrEmpty()) {
                0
            } else {
                text.toString().toInt()
            }
            var outputNumber = inputNumber.times(0.01)
            outputTextView.text = outputNumber.toString()
            if (cmToM) {
                outputTextView.text = inputNumber.times(0.01).toString()
            } else {
                outputTextView.text = inputNumber.times(100).toString()
            }
            Log.d("InputNumber", inputNumber.toString())
//            inputNumber =
////                try {
////                    text.toString().toInt()
////                } catch (e: NumberFormatException) {
////                    0
////                }
        }

        swapImageBtn.setOnClickListener {
            cmToM = cmToM.not()
            if (cmToM) {
                inputUnitTextView.text = "cm"
                outputUnitTextView.text = "m"
                outputTextView.text = inputNumber.times(0.01).toString()
            } else {
                inputUnitTextView.text = "m"
                outputUnitTextView.text = "cm"
                outputTextView.text = inputNumber.times(100).toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmToM", cmToM)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        cmToM = savedInstanceState.getBoolean("cmToM")
        binding.inputUnitTextView.text = if(cmToM) "cm" else "m"
        binding.outputUnitTextView.text = if(cmToM) "m" else "cm"
        super.onRestoreInstanceState(savedInstanceState)
    }
}