package com.inik.mediasearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.inik.mediasearch.databinding.FragmentFavoritesBinding
import com.inik.mediasearch.databinding.FragmentSearchBinding

class FavoritesFragment : Fragment() {
    private var binding: FragmentFavoritesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFavoritesBinding.inflate(inflater, container, false).apply {
            binding = this
        }.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}