package com.inik.presentation.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.inik.presentation.ui.theme.ShoppleTheme

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShoppleTheme {
        MainScreen()
    }
}

@Composable
fun MainScreen() {
}

fun MainBottomNavgationBar