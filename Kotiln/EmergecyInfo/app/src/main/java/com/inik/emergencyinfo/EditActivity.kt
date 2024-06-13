package com.inik.emergencyinfo

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.view.isVisible
import com.inik.emergencyinfo.databinding.ActivityEditBinding


class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BloodTypeSpinner.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.blood_types,
            android.R.layout.simple_list_item_1
        )
        binding.birthDateLayer.setOnClickListener {
            val listener = OnDateSetListener { _, year, month, dayOfMonth ->
                binding.birthDateTextView.text = "$year - ${month.inc()} - $dayOfMonth"
            }
            DatePickerDialog(
                this,
                listener,
                2000,
                1,
                1
            ).show()
        }
        binding.notiCheckBox.setOnCheckedChangeListener { _, isChecked ->
            binding.notificationValueEditTextView.isVisible = isChecked

        }

        binding.notificationValueEditTextView.isVisible = binding.notiCheckBox.isChecked

        binding.saveBtn.setOnClickListener {
            saveData()
            finish()
        }
    }

    private fun saveData() {
        with(getSharedPreferences(USER_INFORMATION, Context.MODE_PRIVATE).edit()) {
            putString(NAME, binding.nameValueEditView.text.toString())
            putString(BLOOD_TYPE,getBloodType())
            putString(EMERGENCY_CONTACT, binding.EmergecyContactValueEditTextView.text.toString())
            putString(BIRTHDATE, binding.birthDateValueEditView.text.toString())
            putString(NOTI_INFO,getNoti())
            apply()
        }
        Toast.makeText(this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun getBloodType(): String{
        val bloodAlpahbat = binding.BloodTypeSpinner.selectedItem.toString()
        val bloodSign = if(binding.BloodTypePlus.isChecked) "+" else "-"
        return "$bloodSign$bloodAlpahbat"
    }

    private fun getNoti(): String{
        return if(binding.notiCheckBox.isChecked) binding.notificationValueEditTextView.text.toString() else ""
    }
}