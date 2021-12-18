package com.test.jetback

sealed class Screen(val route: String)
{
    object Home : Screen(route = "HomeScreen")
    object Setting : Screen(route = "Setting")
}
