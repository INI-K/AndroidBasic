package com.inik.mediasearch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.inik.mediasearch.databinding.FragmentSearchBinding
import com.inik.mediasearch.list.ItemHandler
import com.inik.mediasearch.list.ListAdapter
import com.inik.mediasearch.model.ListItem
import com.inik.mediasearch.repository.SearchRepositoryImpl

class SearchFragment : Fragment() {
    private val viewModel : SearchViewModel by  viewModels{
        SearchViewModel.SearchViewModelFactory(SearchRepositoryImpl(RetrofitManager.searchService))
    }

    private var binding: FragmentSearchBinding? = null

    private val listAdapter by lazy { ListAdapter(Handler(viewModel)) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSearchBinding.inflate(inflater,container,false).apply {
            binding = this
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@SearchFragment.viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            recyclerView.adapter = listAdapter
        }
        observeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    fun searchKeyword(text : String){
        viewModel.search(text)
    }


    private fun observeViewModel(){
        viewModel.listLiveData.observe(viewLifecycleOwner){
            binding?.apply {
                if(it.isEmpty()){
                    Log.e("확인","리스트 없음")
                    emptyTextView.isVisible = true
                    recyclerView.isVisible = false
                }else{
                    Log.e("확인","리스트 있음 : $it")
                    emptyTextView.isVisible = false
                    recyclerView.isVisible = true
                }
            }
            listAdapter.submitList(it?.toMutableList())
        }
    }

    class  Handler(private val viewModel: SearchViewModel) : ItemHandler {
        override fun onClickFavorite(item: ListItem) {
            viewModel.toggleFavorites(item)
        }
    }
}