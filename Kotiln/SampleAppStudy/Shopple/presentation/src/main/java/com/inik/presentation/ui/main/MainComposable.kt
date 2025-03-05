package com.inik.presentation.ui.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.inik.domain.model.Product
import com.inik.presentation.ui.common.ProductCard
import com.inik.presentation.viewmodel.MainViewModel

@Composable
fun MainInsideScreen(viewModel: MainViewModel) {
    val productList by viewModel.productList.collectAsState(initial = listOf())
    val columnCount by viewModel.columnCount.collectAsState()
    LazyVerticalGrid(columns = GridCells.Fixed(columnCount)) {
        items(productList.size) {
            ProductCard(product = productList[it]) {
                //상세화면 개발시 추가
            }
        }
    }

}