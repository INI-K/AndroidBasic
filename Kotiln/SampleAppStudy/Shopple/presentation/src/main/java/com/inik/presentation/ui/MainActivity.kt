package com.inik.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.inik.presentation.ui.theme.ShoppleTheme
import com.inik.presentation.viewmodel.MainViewModel
import com.inik.presentation.viewmodel.TempViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    private val viewModel: TempViewModel by viewModels()

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShoppleTheme {
                MainScreen( )
            }
        }
        viewModel.updateColumnCount(getColumnCount())
    }

    private fun getColumnCount(): Int {
        return getDisplayWidthDp().toInt() / DEFAULT_COLUMN_SIZE
    }
    private fun getDisplayWidthDp():Float{
        return resources.displayMetrics.run {widthPixels / density}
    }

    companion object {
        private const val  DEFAULT_COLUMN_SIZE = 180
    }
}
