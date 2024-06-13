package com.inik.emergencyinfo

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import com.inik.emergencyinfo.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dexOutputDir: File = codeCacheDir
        dexOutputDir.setReadOnly()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.moveInputActivity.setOnClickListener {
            Log.d(TAG,"버튼눌림?")
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }
        binding.deleteBtn.setOnClickListener {
            deleteData()
        }
        binding.emergecyContactLayer.setOnClickListener{
            val callIntent = with(Intent(Intent.ACTION_VIEW)){
                val phoneNum = binding.EmergecyContactValueTextView.text.toString()
                    .replace("-","")
                data= Uri.parse("tel:$phoneNum")
                startActivity(this)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getDataUiUpdate()
    }

    private fun getDataUiUpdate(){
        with(getSharedPreferences("userInformation", Context.MODE_PRIVATE)){
            binding.nameValueTextView.text = getString(NAME,"미정")
            binding.birthDateValueTextView.text = getString(BIRTHDATE,"미정")
            binding.BloodTypeValueTextView.text = getString(BLOOD_TYPE,"미정")
            binding.EmergecyContactValueTextView.text = getString(EMERGENCY_CONTACT, "미정")

            val noti = getString(NOTI_INFO,"미정")

            binding.notificationTextView.isVisible = noti.isNullOrEmpty().not()
            binding.notificationValueTextView.isVisible = noti.isNullOrEmpty().not()

            if(!noti.isNullOrEmpty()){
                binding.notificationValueTextView.text = noti
            }
        }
    }

    private fun deleteData(){
        with(getSharedPreferences(USER_INFORMATION, MODE_PRIVATE).edit()){
            clear()
            apply()
            getDataUiUpdate()
        }
        Toast.makeText(this, "초기화를 완료했습니다.", Toast.LENGTH_SHORT).show()
    }
}