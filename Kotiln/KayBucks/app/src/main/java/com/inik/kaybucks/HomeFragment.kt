package com.inik.kaybucks

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.inik.kaybucks.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        val homeData = context?.readData("home.json", Home::class.java) ?: return
        val menuData = context?.readData("menu.json", Menu::class.java) ?: return

        initAppBar(homeData)
        initRecomentMenuList(homeData, menuData)
        initBanner(homeData)
        initFoodList(homeData, menuData)

        binding.scrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if(scrollY == 0){
                binding.floatingActionBtn.extend()
            }else{
                binding.floatingActionBtn.shrink()
            }
        }
    }

    private fun initFoodList(homeData: Home, menuData: Menu) {
        binding.foodMenuList.titleTextView.text =
            getString(R.string.food_menu_title, homeData.user.nickname)
        menuData.food.forEach { menuItem ->
            binding.foodMenuList.menuLayout.addView(
                MenuView(context = requireContext()).apply {
                    setTitle(menuItem.name)
                    setImageView(menuItem.image)
                }
            )
        }
    }

    private fun initBanner(homeData: Home) {
        binding.bannerLayout.bannerImageView.apply {
            Glide.with(this)
                .load(homeData.banner.image)
                .into(this)
            this.contentDescription = homeData.banner.contentDescription
        }
    }

    private fun initRecomentMenuList(homeData: Home, menuData: Menu) {
        binding.recommendMenuList.titleTextView.text =
            getString(R.string.recommend_title, homeData.user.nickname)
        menuData.coffee.forEach { menuItem ->
            binding.recommendMenuList.menuLayout.addView(
                MenuView(context = requireContext()).apply {
                    setTitle(menuItem.name)
                    setImageView(menuItem.image)
                }
            )
        }
    }

    private fun initAppBar(homeData: Home) {
        binding.appbarTextView.text =
            getString(R.string.appbarTitleText, homeData.user.nickname)

        binding.starCountTextView.text =
            getString(
                R.string.appbar_star_title,
                homeData.user.startCount,
                homeData.user.totalCount
            )


        binding.appbarProgressBar.max = homeData.user.totalCount


        Glide.with(binding.appbarImageView)
            .load(homeData.appbarImage)
            .into(binding.appbarImageView)

        ValueAnimator.ofInt(0,homeData.user.startCount).apply {
            duration = 1000
            addUpdateListener {
                binding.appbarProgressBar.progress = it.animatedValue as Int
            }
            start()
        }
    }
}