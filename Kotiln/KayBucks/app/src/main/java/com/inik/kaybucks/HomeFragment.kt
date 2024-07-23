package com.inik.kaybucks

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

        val homeData = context?.readData() ?: return

        binding.appbarTextView.text =
            getString(R.string.appbarTitleText, homeData.user.nickname)

        binding.starCountTextView.text =
            getString(R.string.appbar_star_title, homeData.user.startCount, homeData.user.totalCount)

        binding.appbarProgressBar.progress = homeData.user.startCount
        binding.appbarProgressBar.max = homeData.user.totalCount


        Glide.with(binding.appbarImageView)
            .load(homeData.appbarImage)
            .into(binding.appbarImageView)
    }
}