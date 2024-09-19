package com.inik.mediasearch

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import com.google.android.material.tabs.TabLayoutMediator
import com.inik.mediasearch.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val searchFragment = SearchFragment()
    private val fragmentList = listOf(searchFragment,FavoritesFragment())
    private val adapter = ViewPagerAdapter(supportFragmentManager,lifecycle,fragmentList)

    private var observableTextQuery : Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView(){
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            view = this@MainActivity
            viewPager.adapter = adapter

            TabLayoutMediator(tabLayout, viewPager){tab, position ->
                tab.text = if(fragmentList[position] is SearchFragment){
                    "검색 결과"
                }else{
                    "즐겨 찾기"
                }
            }.attach()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        observableTextQuery?.dispose()
        observableTextQuery = null
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu,menu)

        observableTextQuery = Observable.create { emitter : ObservableEmitter<String>? ->
            (menu.findItem(R.id.search).actionView as SearchView).apply {
                setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String): Boolean { // 완료버튼을 누를때
                        emitter?.onNext(query)
                        return false
                    }

                    override fun onQueryTextChange(newText: String): Boolean { // 텍스트가 변경될때
                        binding.viewPager.setCurrentItem(0,true)
                        emitter?.onNext(newText)
                        return false
                    }
                })
            }
        }
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                searchFragment.searchKeyword(it)
            }
        return true
    }
}