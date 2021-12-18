package com.nkstudio.movingnumber.screenmove

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.nkstudio.movingnumber.myview.*

@ExperimentalPermissionsApi
@Composable
fun NavigationGraph(
    navController: NavHostController
)
{
    val callLogState = rememberPermissionState(permission = Manifest.permission.READ_CALL_LOG)
    val callPhoneState = rememberPermissionState(permission = Manifest.permission.CALL_PHONE)

    @Composable
    fun initScreen(): String
    {
        if (!callLogState.hasPermission)
        {
            return Screen.callLogPermission.route
        }
        if (!callPhoneState.hasPermission)
        {
            return Screen.callPhonePermission.route
        }
        return Screen.Home.route
    }

    NavHost(
        navController = navController,
        startDestination = initScreen()
    ) {
        composable(route = Screen.callLogPermission.route)
        {
            callLogPermissionView(navController)
        }
        composable(route = Screen.callPhonePermission.route)
        {
            callPhonePermissionView(navController)
        }
        composable(route = Screen.Home.route)
        {
            MainView(
                navController = navController,
                context = LocalContext.current
            )
        }
        composable(route = Screen.SettingDetail.route)
        {
            SettingDetailView(navController)
        }
        composable(route = Screen.NumberSettingViewDetail.route)
        {
            NumberSettingView(navController)
        }
        composable(route = Screen.Setting.route)
        {
            SettingView(navController)
        }
        composable(route = Screen.Call.route)
        {
            CallView()
        }
    }
}