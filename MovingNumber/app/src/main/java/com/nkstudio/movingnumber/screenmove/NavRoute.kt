package com.nkstudio.movingnumber.screenmove

sealed class Screen(val route: String)
{
    object callLogPermission : Screen(route = "callLogPermissionScreen")
    object callPhonePermission : Screen(route = "callPhonePermissionScreen")
    object Home : Screen(route = "HomeScreen")
    object SettingDetail : Screen(route = "SettingDetailScreen")
    object NumberSettingViewDetail : Screen(route = "NumberSettingDetailScreen")
    object Setting : Screen(route = "SettingScreen")
    object Call : Screen(route = "CallScreen")
}
