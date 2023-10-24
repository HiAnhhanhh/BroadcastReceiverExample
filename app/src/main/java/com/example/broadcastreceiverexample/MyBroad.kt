package com.example.broadcastreceiverexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroad : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        val toastMessage = when (action){
            Intent.ACTION_POWER_DISCONNECTED -> "Power disconnected"
            Intent.ACTION_POWER_CONNECTED -> "Power connected"
            else -> ""
        }
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
    }
}