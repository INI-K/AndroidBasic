package com.inik.gitlookup

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.inik.gitlookup.adapter.UserAdapter
import com.inik.gitlookup.databinding.ActivityMainBinding
import com.inik.gitlookup.model.Repo
import com.inik.gitlookup.model.UserDto
import com.inik.gitlookup.network.GIthubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var gIthubService: GIthubService
    private lateinit var userAdapter: UserAdapter
    private var retrofit =  Retrofit.Builder().baseUrl("https://api.github.com/")
    .addConverterFactory(GsonConverterFactory.create()).build()

    private var searchFor: String = ""
    private val handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gIthubService = retrofit.create(GIthubService::class.java)

//        gIthubService.listRepos("square").enqueue(object : Callback<List<Repo>> {
//            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
//                Log.e("MainActivity", response.body().toString())
//            }
//
//            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
//
//            }
//
//        })

        userAdapter = UserAdapter{
            val intent = Intent(this@MainActivity, RepoActivity::class.java)
            intent.putExtra("username",it.userName)
            startActivity(intent)
        }

        binding.userRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }

        val runnable = Runnable {
            searchUser()
        }

        binding.searchEditText.addTextChangedListener {
            searchFor = it.toString()
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable,300)
        }
    }

    private fun searchUser() {
        gIthubService.searchUsers(searchFor).enqueue(object : Callback<UserDto> {
            override fun onResponse(call: Call<UserDto>, response: Response<UserDto>) {
                Log.e("유저 검색", "유저 :  ${response.body().toString()}")

                userAdapter.submitList(response.body()?.items)
            }

            override fun onFailure(call: Call<UserDto>, t: Throwable) {
                Toast.makeText(this@MainActivity, "에러가 발생했읍니다이",Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

        })
    }
}