package com.nkstudio.movingnumber.myservice

import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import android.provider.CallLog
import android.util.Log
import com.nkstudio.movingnumber.MainActivity
import com.nkstudio.movingnumber.MainActivity.Companion.finishShow
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ShowYourNumber : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("로그","ShowYourNumber 시작됨")
        val phoneNb: String? = getCallNumber(applicationContext)

        val callIntent = Intent(Intent.ACTION_DIAL)
        callIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_SINGLE_TOP or
                Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(callIntent)

        MainScope().launch {

            delay(5000)
            val showNumberIntent = Intent(Intent.ACTION_DIAL)
            showNumberIntent.data = Uri.parse("tel:$phoneNb")
            showNumberIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_SINGLE_TOP or
                    Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(showNumberIntent)
            //Log.d("로그","${phoneNb} 따란")

            delay(2000)
            val callNumberIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNb"))
            callNumberIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_SINGLE_TOP or
                    Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(callNumberIntent)
            //Log.d("로그","${phoneNb} 전화")
            finishShow = true
            stopSelf()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun getCallNumber(context: Context): String {
        var audioPhone = ""
        val cursor =
            context.contentResolver.query(
                CallLog.Calls.CONTENT_URI,
                null,
                null,
                null,
                null
            ) //최근 통화 목록중에서

        val number = cursor?.getColumnIndex(CallLog.Calls.NUMBER)
        val type = cursor?.getColumnIndex(CallLog.Calls.TYPE)
        while (cursor?.moveToNext() == true) {
            val phoneNumber = number?.let { cursor.getString(it) }
            val phoneType = type?.let { cursor.getString(it) }
            when (Integer.parseInt(phoneType)) {
                CallLog.Calls.MISSED_TYPE -> {
                    audioPhone = phoneNumber.toString()
                }
            }
        }
        return audioPhone
    }

    override fun onDestroy()
    {
        super.onDestroy()
        Log.d("로그","ShowYourNumber 종료됨")
    }
}