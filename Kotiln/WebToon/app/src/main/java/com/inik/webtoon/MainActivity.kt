package com.inik.webtoon

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.inik.webtoon.WebViewFragment.Companion.SHARED_PREFERENCES
import com.inik.webtoon.databinding.ActivityMainBinding
import com.inik.webtoon.databinding.FragmentWebviewBinding

class MainActivity : AppCompatActivity(),OnTabLayoutNameChanged {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences(WebViewFragment.Companion.SHARED_PREFERENCES,Context.MODE_PRIVATE)
        val tab0 = sharedPreferences.getString("tab0_name","월요 웹툰")
        val tab1 = sharedPreferences.getString("tab1_name","화요 웹툰")
        val tab2 = sharedPreferences.getString("tab2_name","수요 웹툰")


        binding.viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout,binding.viewPager){ tab, position ->
            run {
//                val textView = TextView(this@MainActivity)
//                textView.text = "position $position"
//                textView.gravity = Gravity.CENTER
//
//                tab.customView = textView
                tab.text = when(position){
                    0 -> { tab0 }
                    1 -> { tab1 }
                    else -> { tab2 }
                }
            }
        }.attach()
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.fragments[binding.viewPager.currentItem]
        if (currentFragment is WebViewFragment) {
            if (currentFragment.canGoback()) {
                currentFragment.goBack()
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
//        super.onBackPressed()
    }

    override fun nameChanged(position: Int, name: String) {
        Log.d("실행 확인 " , "번호 : $position")
        val tab = binding.tabLayout.getTabAt(position)
        tab?.text = name
    }
}
