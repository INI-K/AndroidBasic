package com.inik.presentation.ui


import android.net.http.HeaderBlock
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.inik.presentation.R

import com.inik.presentation.ui.main.MainInsideScreen

import com.inik.presentation.ui.theme.ShoppleTheme
import com.inik.presentation.viewmodel.MainViewModel

sealed class MainNavigationItem(var route: String, var Icon: ImageVector, var name: String) {
    object Main : MainNavigationItem("Main", Icons.Filled.Home, "Main")
    object Category : MainNavigationItem("Category", Icons.Filled.Star, "Category")
    object MyPage : MainNavigationItem("MyPage", Icons.Filled.AccountBox, "MyPage")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShoppleTheme {
        MainScreen()
    }
}

@Suppress("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val viewModel = hiltViewModel<MainViewModel>()
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            Header(viewModel)
        },
        scaffoldState = scaffoldState,
        bottomBar = {
            MainBottomNavigationBar(navController)
        }
    ) {
        MainNavigationScreen(mainViewModel = viewModel,navController)
    }
}

@Composable
fun Header(viewModel: MainViewModel) {
    TopAppBar(title = { Text("Shopple") },
        actions = {
            IconButton(onClick = {
                viewModel.openSearchForm()
            }) {
                Icon(Icons.Filled.Search, "SearchAcition")
            }
        }
    )
}

@Composable
fun MainBottomNavigationBar(navController: NavHostController) {
    val bottomNavigationItems = listOf(
        MainNavigationItem.Main,
        MainNavigationItem.Category,
        MainNavigationItem.MyPage,
    )

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomNavigationItems.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }

                },
                icon = { Icon(item.Icon, item.route) },
            )
        }
    }
}

@Composable
fun MainNavigationScreen(mainViewModel: MainViewModel,navController: NavHostController) {
    NavHost(navController = navController, startDestination = MainNavigationItem.Main.route) {
        composable(MainNavigationItem.Main.route) {
            MainInsideScreen(mainViewModel)
        }
        composable(MainNavigationItem.Category.route) {
            Text(text = "Hello Category")
        }
        composable(MainNavigationItem.MyPage.route) {
            Text(text = "Hello MyPage")
        }
    }
}