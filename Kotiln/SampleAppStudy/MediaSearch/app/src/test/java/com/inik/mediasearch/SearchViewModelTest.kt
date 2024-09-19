package com.inik.mediasearch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.inik.mediasearch.model.ImageItem
import com.inik.mediasearch.model.ListItem
import com.inik.mediasearch.model.VideoItem
import com.inik.mediasearch.repository.SearchRepository
import io.reactivex.rxjava3.core.Observable

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.Date

@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var searchRepository: SearchRepository

    @Mock
    lateinit var mockLoadingObserver : Observer<Boolean>

    @Mock
    lateinit var  mockListObserver : Observer<List<ListItem>>

    private lateinit var viewModel: SearchViewModel


    @Before
    fun setUp(){
        viewModel = SearchViewModel(searchRepository)
        viewModel.showLoading.observeForever(mockLoadingObserver)
        viewModel.listLiveData.observeForever(mockListObserver)
    }


    @Test
    fun searchNotEmpty() {
        Mockito.`when`(searchRepository.search(Mockito.anyString())).thenReturn(Observable.just(mockLick()))
        viewModel.search("query")

        Mockito.verify(mockLoadingObserver,Mockito.times(1)).onChanged(true)
        Mockito.verify(mockListObserver,Mockito.times(1)).onChanged(Mockito.anyList())
        assertTrue(!viewModel.listLiveData.value.isNullOrEmpty())
    }
    @Test
    fun searchEmpty() {
        Mockito.`when`(searchRepository.search(Mockito.anyString())).thenReturn(Observable.just(
            emptyList()
        ))
        viewModel.search("query")
        Mockito.verify(mockLoadingObserver,Mockito.times(1)).onChanged(true)
        Mockito.verify(mockListObserver,Mockito.times(1)).onChanged(Mockito.anyList())
        assertTrue(viewModel.listLiveData.value.isNullOrEmpty())
    }

    private fun mockLick() = listOf(
        ImageItem("thumNailUrl", "collection","siteName","docUrl", Date(),false),
        ImageItem("thumNailUrl1", "collection1","siteName1","docUr1l", Date(),true),
        VideoItem("썸네일 ","타이틀",3,"작성자",Date(),false)
    )
}