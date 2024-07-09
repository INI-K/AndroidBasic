package com.inik.myweather

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.net.URLDecoder
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.inik.myweather.databinding.ActivityMainBinding
import com.inik.myweather.databinding.ItemForecastBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var binding: ActivityMainBinding

    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                updateLocation()
            }

            else -> {
                Toast.makeText(this , "위치 권한이 필요합니다",Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_APPLICATION_SETTINGS).apply {
                    data = Uri.fromParts("package", packageName, null)
                }
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION))
//         gson = GsonBuilder().setLenient().create()



    }


    private fun updateLocation(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION))
            return
        }
         fusedLocationClient.lastLocation.addOnSuccessListener {

             Thread{
                 try {
                     val addresList =  Geocoder(this, Locale.KOREA).getFromLocation(
                         it.latitude,
                         it.longitude,
                         1)

                     runOnUiThread {
                         binding.locationTextView.text = addresList?.get(0)?.featureName
                     }
                     Log.e("주소확인", addresList?.get(0)?.toString().orEmpty())
                 }catch (e: Exception){
                     e.printStackTrace()
                 }
             }.start()

             WeatherRepository.getVillageForecase(
                 latitude = it.latitude,
                 longitude = it.longitude,
                 successCallback = { list ->
                     val currentForecast = list.first()
                     binding.temperatureTextView.text = getString(R.string.temperature_text, currentForecast.temperature)
                     binding.skyTextView.text = currentForecast.weather
                     binding.precipitationTextView.text = getString(R.string.precipitation_text, currentForecast.precipitation)

                     binding.childForecastLayout.apply {
                         list.forEachIndexed{index, forecast ->
                             if(index == 0){
                                 return@forEachIndexed
                             }

                             val itemView = ItemForecastBinding.inflate(layoutInflater)

                             itemView.timeTextView.text = forecast.forecastTime
                             itemView.weatherTextView.text = forecast.weather
                             itemView.temperatureTextView.text =
                                 getString(R.string.temperature_text, forecast.temperature)

                             addView(itemView.root)
                         }
                     }
                 },
                 failureCallback = {
                     it.printStackTrace()
                 })


            val baseDateTime = BaseDateTime.getBaseDateTime()
            val converter = GeoPointConverter()
            val point = converter.convert(lat = it.latitude, lon = it.longitude)
        }
    }
}