package com.test.jetback

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationGraph(
    navController: NavHostController
)
{
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                MainView(navController)
        }
        composable(
            route = Screen.Setting.route
        ) {
            SettingView()
        }
    }
}