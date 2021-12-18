package com.test.jetback

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.test.jetback.ui.theme.JetbackTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity()
{
    companion object
    {
        val photoUri = arrayOfNulls<String>(3)
    }

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        //시스템 UI 숨김
        HideSystemUI(window)

        val saveSystem = SaveSystem(this)

        GlobalScope.launch {
            photoUri[0] = saveSystem.read("photo")

            if (photoUri[0] != null)
                Log.d("로그", photoUri[0].toString())
        }

        setContent {
            JetbackTheme {
                navController = rememberNavController()
                NavigationGraph(navController)
            }
        }
    }
}

fun HideSystemUI(window: Window)
{
    WindowCompat.setDecorFitsSystemWindows(window, false)
    val controllerCompat = WindowInsetsControllerCompat(window, window.decorView)
    controllerCompat.hide(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.navigationBars())
    controllerCompat.systemBarsBehavior =
        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
    {
        window.attributes.layoutInDisplayCutoutMode =
            WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
    }
}