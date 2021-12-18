package com.nkstudio.movingnumber.myservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.nkstudio.movingnumber.MainActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GoHomeService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {
        val homeintent = Intent(Intent.ACTION_MAIN) //태스크의 첫 액티비티로 시작
        homeintent.addCategory(Intent.CATEGORY_HOME)   //홈화면 표시
        homeintent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(homeintent)
        Log.d("로그","GoHomeService 시작됨")
        
        MainScope().launch {
            Log.d("로그","스코프 진행")
            delay(2000)
            Log.d("로그","2초 지남")
            val callintent = Intent(applicationContext, MainActivity::class.java) //태스크의 첫 액티비티로 시작
            callintent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_SINGLE_TOP or
                    Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(callintent)
        }

        stopSelf()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy()
    {
        super.onDestroy()
        Log.d("로그","GoHomeService 종료됨")
    }
}