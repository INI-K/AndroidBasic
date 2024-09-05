package com.inik.foodpoint

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.inik.foodpoint.databinding.ActivityMainBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.Tm128
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var naverMap: NaverMap
    private var isMapInit = false

    private var resturantListAdapter = RasturantsAdapter{
        val cameraUpdate = CameraUpdate.scrollTo(it)
            .animate(CameraAnimation.Easing)
        naverMap.moveCamera(cameraUpdate)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(this)

        binding.bottomsheetlayout.searchResultRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
                adapter = resturantListAdapter
        }


        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query?.isNotEmpty() == true) {
                    SearchRepository.getGoodRestaurant(query)
                        .enqueue(object : Callback<SearchResult> {
                            override fun onResponse(
                                call: Call<SearchResult>,
                                response: Response<SearchResult>
                            ) {
                                Log.e("맛집 확인", "${response.body().toString()}")

                                val serachItemList = response.body()?.items.orEmpty()
                                if (serachItemList.isEmpty()) {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "검색 결과가 없습니다",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    return
                                }else if(isMapInit.not()){
                                    Toast.makeText(
                                        this@MainActivity,
                                        "오류가 발생했습니다.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                               val markers = serachItemList.map {
                                   val latLng = LatLng(it.mapy.toDouble() / 10000000, it.mapx.toDouble() / 10000000)
                                   Marker(latLng).apply {
                                        captionText = it.title
                                        map = naverMap
                                    }
                                }
                                Log.e("위치확인", "X값 : ${serachItemList.first().mapx.toDouble()/ 10000000}, Y값 : ${serachItemList.first().mapy.toDouble()/ 10000000}")
                                Log.e("위치확인", "값 확인 : ${Tm128(serachItemList.first().mapx.toDouble(),serachItemList.first().mapy.toDouble()).toLatLng()}")

                                resturantListAdapter.setData(serachItemList)
                                resturantListAdapter.notifyItemRangeChanged(0,serachItemList.size)

                                val cameraUpdate = CameraUpdate.scrollTo(markers.first().position)
                                    .animate(CameraAnimation.Easing)
                                naverMap.moveCamera(cameraUpdate)
                            }

                            override fun onFailure(call: Call<SearchResult>, t: Throwable) {

                            }

                        })
                    return false
                } else {
                    return true
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    override fun onMapReady(mapObject: NaverMap) {
        naverMap = mapObject
        isMapInit = true
//        val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.5666102, 126.9783881))
//            .animate(CameraAnimation.Easing)
//        naverMap.moveCamera(cameraUpdate)
    }
}