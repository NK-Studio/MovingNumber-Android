package com.nkstudio.movingnumber

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.nkstudio.movingnumber.MainActivity.Companion.phoneNumber
import com.nkstudio.movingnumber.myservice.GoHomeService
import com.nkstudio.movingnumber.myservice.ShowYourNumber
import com.nkstudio.movingnumber.savesystem.SaveSystem
import com.nkstudio.movingnumber.screenmove.NavigationGraph
import com.nkstudio.movingnumber.screenmove.Screen
import com.nkstudio.movingnumber.ui.theme.MovingNumberTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity()
{
    companion object
    {
        val photoUri = arrayOfNulls<String>(3)
        var phoneNumber = "01012345678"
        var startCall = false
        var finishShow = false
    }

    private lateinit var navController: NavHostController

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        //시스템 UI 숨김
        hideSystemUI(window)

        val saveSystem = SaveSystem(this)

        lifecycleScope.launch {
            for (i: Int in 0..2)
            {
                photoUri[i] = saveSystem.read("photo[${i}]")
            }

            phoneNumber = saveSystem.read("number").toString()
            if(phoneNumber == "null")
                phoneNumber = "01012345678"
        }
        setContent {
            MovingNumberTheme() {
                navController = rememberNavController()
                NavigationGraph(navController)
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean
    {
        when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_UP -> {
                return if (!finishShow && navController.currentDestination?.route == Screen.Call.route) {
                    startCall = true
                    showMyNb(applicationContext)
                    true
                } else
                    super.onKeyDown(keyCode, event)
            }
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                return if (!finishShow && navController.currentDestination?.route == Screen.Call.route) {
                    showYourNumber(applicationContext)
                    true
                } else
                    super.onKeyDown(keyCode, event)
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy()
    {
        super.onDestroy()
        val GoHomeIntent = Intent(applicationContext,GoHomeService::class.java)
        stopService(GoHomeIntent)

        val ShowYourIntent = Intent(applicationContext, ShowYourNumber::class.java)
        stopService(ShowYourIntent)
        Log.d("로그","서비스를 종료 시킴")
    }
}


fun showMyNb(context: Context) {
    val intent = Intent(Intent.ACTION_DIAL)
    intent.data = Uri.parse("tel:${phoneNumber}")
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
            Intent.FLAG_ACTIVITY_SINGLE_TOP or
            Intent.FLAG_ACTIVITY_NEW_TASK
    context.startActivity(intent)
}



fun showYourNumber(context: Context) {
    val intent = Intent(context, ShowYourNumber::class.java)
    context.startService(intent)
}

fun hideSystemUI(window: Window) {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    val controllerCompat = WindowInsetsControllerCompat(window, window.decorView)
    controllerCompat.hide(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.navigationBars())
    controllerCompat.systemBarsBehavior =
        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

    window.attributes.layoutInDisplayCutoutMode =
        WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
}