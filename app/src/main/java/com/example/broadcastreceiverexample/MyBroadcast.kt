package com.example.broadcastreceiverexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcast  : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        when (action){
            "Hi_Anhhanhh" -> {
                val string = intent?.getStringExtra("ex1")
                Toast.makeText(context, "Receiver app receiver : ${string} ", Toast.LENGTH_SHORT).show()
            }
        }
    }
}