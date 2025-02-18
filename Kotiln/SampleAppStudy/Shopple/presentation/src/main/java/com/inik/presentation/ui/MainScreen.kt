package com.inik.presentation.ui


import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.inik.presentation.R

import com.inik.presentation.ui.theme.ShoppleTheme

sealed class MainNavigationItem(var route: String, var name: String) {
    object Main : MainNavigationItem("Main", "Main")
    object Category : MainNavigationItem("Category", "Category")
    object MyPage : MainNavigationItem("MyPage", "MyPage")
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
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    Scaffold(
        scaffoldState =  scaffoldState,
        bottomBar = {
            MainBottomNavigationBar(navController)
        }
    ) {
        MainNavigationScreen(navController)
    }
}

@Composable
fun MainBottomNavigationBar(navController: NavHostController) {
    val bottomNavigationItems = listOf(
        MainNavigationItem.Main,
        MainNavigationItem.Category,
        MainNavigationItem.MyPage,
    )

    BottomNavigation(
        backgroundColor = Color(0xffff0000),
        contentColor = Color(0xff00ff00)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomNavigationItems.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route, onClick = {
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
                icon = { Icon(painterResource(id = R.drawable.ic_android_black_24dp),item.route) },
            )
        }
    }
}

@Composable
fun MainNavigationScreen(navController: NavHostController) {
    NavHost(navController = navController, startDestination = MainNavigationItem.Main.route){
        composable(MainNavigationItem.Main.route){
            Text(text = "Hello Main")
        }
        composable(MainNavigationItem.Category.route){
            Text(text = "Hello Category")
        }
        composable(MainNavigationItem.MyPage.route){
            Text(text = "Hello MyPage")
        }
    }
}