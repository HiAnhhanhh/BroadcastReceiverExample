package com.example.broadcastreceiverexample

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.broadcastreceiverexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
     private val binding by lazy {
         ActivityMainBinding.inflate(layoutInflater)
     }

//    private val receiver : MyBroad = MyBroad()
//    private val mReceiver : MyBroadcast = MyBroadcast()

    lateinit var channelId : String
    lateinit var textTitle : String
    lateinit var textContent : String
    lateinit var string : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        channelId = "message notification"
        textTitle = "Notification Title"
        textContent = "Arsenal No.1"


//        val intentFilter = IntentFilter()
//        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
//        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
//        registerReceiver(broadcastReceiver, intentFilter)

        val intentFilter2 = IntentFilter(FIRST_ACTION)
        registerReceiver(broadcastReceiver, intentFilter2)
//
//        registerReceiver(broadcastReceiver, IntentFilter(FIRST_ACTION))

        binding.sendDataBtn.setOnClickListener {
            val intent = Intent (FIRST_ACTION)
            intent.putExtra("ex1","Broadcast Receiver")
            sendBroadcast(intent)
        }



        binding.sendNotification.setOnClickListener {

            val intent = Intent(this, Notification::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent : PendingIntent = PendingIntent.getActivity(this,0, intent,PendingIntent.FLAG_IMMUTABLE)

            createNotificationChannel()

            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.baseline_favorite_24)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            val builderEnergy  = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.baseline_favorite_24)
                .setContentTitle(textTitle)
                .setContentText(string)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(this)){
                if (ActivityCompat.checkSelfPermission(
                        this@MainActivity,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {

                }else{
                    notify(1, builder.build())
                    notify(2, builderEnergy.build())
                }
            }

        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private val broadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action){
                "Hi_Anhhanhh" -> {
                    string = intent?.getStringExtra("ex1").toString()
                    Log.d("check", "onReceive: ${string} ")
                }
            }
        }
    }

    private fun test(){
        Log.d("tuanminh", "handle event from notification ")
    }

    companion object {
        const val FIRST_ACTION = "Hi_Anhhanhh"
    }
}