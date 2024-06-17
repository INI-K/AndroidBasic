package com.inik.myphotoframe

import android.app.Activity
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.inik.myphotoframe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val imageLoadLauncher = registerForActivityResult(ActivityResultContracts.GetMultipleContents()){
        uriList ->
        updateImages(uriList)
    }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loadImageBtn.setOnClickListener {
            checkPermission()
        }
    }

    private fun checkPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                loadImage()
            }

            shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) -> {
                Log.d("권한 필요","11")
                showPermissionInfoDialog()
            }
            else -> {
                requestReadExternalStorage()
            }
        }
    }

    private fun showPermissionInfoDialog(){
        AlertDialog.Builder(this).apply {
            setMessage("이미지를 가져오기 위해서, 외부 저장소 읽기 권한이 필요합니다.")
            setNegativeButton("취소", null)
            setPositiveButton("동의"){_,_ ->
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    READ_EXTERNAL_STORAGE
                )
            }
        }.show()
    }

    private fun requestReadExternalStorage(){
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            READ_EXTERNAL_STORAGE
        )
    }

    private fun loadImage() {
       imageLoadLauncher.launch("image/*")
    }

    private fun updateImages(uriList: List<Uri>){
        Log.i("이미지 확인", "$uriList")
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            READ_EXTERNAL_STORAGE -> {
                if(grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED){
                    loadImage()
                }
            }
        }
    }

    companion object {
        const val READ_EXTERNAL_STORAGE = 100
    }
}