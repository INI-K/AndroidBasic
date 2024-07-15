package com.inik.zenli

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.database
import com.inik.zenli.databinding.ActivityMainBinding
import com.kakao.sdk.common.util.Utility

class MainActivity : AppCompatActivity(), OnMapReadyCallback ,OnMarkerClickListener{
    private lateinit var binding: ActivityMainBinding
    private lateinit var googleMap: GoogleMap
    private var trackinPersonId: String =""
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val markerMap = hashMapOf<String, Marker>()
    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permission ->
        when {
            permission.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                //fine location 권한있음
                getCurrentLocation()
            }
            permission.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                //coarse 권한있음
                getCurrentLocation()
            }
            else -> {
                //팝업
            }
        }
    }
    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)

            //새로 요청된 위치 정보
            for (location in locationResult.locations) {
                Log.e("맵 앱티비티", "위치 ${location.latitude},${location.longitude}")

                //파이어베이스 내위치 업로드
                val uId = Firebase.auth.currentUser?.uid.orEmpty()
                val locationMap = mutableMapOf<String, Any>()
                locationMap["latitude"] = location.latitude
                locationMap["longitude"] = location.longitude
                Firebase.database.reference.child("Person").child(uId).updateChildren(locationMap)

                //지도에 마커 움직이기


            }
        }
    }

    override fun onResume() {
        super.onResume()
        getCurrentLocation()
    }

    override fun onPause() {
        super.onPause()
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        requestLocationPermissions()
        setupFirebaseDatabase()
    }

    private fun getCurrentLocation() {
        val locationRequest = LocationRequest
            .Builder(Priority.PRIORITY_HIGH_ACCURACY, 5 * 1000)
            .build()

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermissions()
            return
        }
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            googleMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(LatLng(it.latitude,it.longitude),16.0f)
            )
        }
    }

    private fun requestLocationPermissions() {
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun setupFirebaseDatabase(){
        Firebase.database.reference.child("Person").addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val person = snapshot.getValue(Person::class.java) ?: return
                val uid = person.uId ?:return


                if(markerMap[uid] == null){
                    markerMap[uid] = makeNewMaker(person,uid)?: return
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val person = snapshot.getValue(Person::class.java) ?: return
                val uid = person.uId ?:return

                if(markerMap[uid] == null){
                    markerMap[uid] = makeNewMaker(person,uid)?: return
                }else{
                    markerMap[uid]?.position = LatLng(person.latitude ?: 0.0,person.longitude?: 0.0)
                }

                if(uid == trackinPersonId){
                    googleMap.animateCamera(
                        CameraUpdateFactory.newCameraPosition(
                            CameraPosition.Builder()
                                .target(LatLng(person.latitude ?: 0.0,person.longitude?: 0.0))
                                .zoom(16.0f)
                                .build()
                        )
                    )
                }
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
    private fun makeNewMaker(person: Person, uid: String): Marker?{
        val marker = googleMap.addMarker(
            MarkerOptions().position(LatLng(person.latitude ?: 0.0,person.longitude?: 0.0))
                .title(person.name.orEmpty())
        )?: return null

        marker.tag = uid

        Glide.with(this).asBitmap()
            .load(person.profilePhoto)
            .transform(RoundedCorners(60))
            .override(200)
            .listener(object : RequestListener<Bitmap>{
                override fun onLoadFailed(
                    exception: GlideException?,
                    p1: Any?,
                    target: Target<Bitmap>,
                    p3: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap,
                    p1: Any,
                    p2: Target<Bitmap>?,
                    p3: DataSource,
                    p4: Boolean
                ): Boolean {
                    runOnUiThread{
                        resource.let {
                            marker.setIcon(
                                BitmapDescriptorFactory.fromBitmap(
                                    resource
                                )
                            )
                        }
                    }
                    return true
                }
            }).submit()

        return marker
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.setMaxZoomPreference(20.0f)
        googleMap.setMinZoomPreference(10.0f)

        googleMap.setOnMarkerClickListener(this)
        googleMap.setOnMapClickListener {
            trackinPersonId = ""
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {

        trackinPersonId = marker.tag as? String ?: ""


        return false
    }
}