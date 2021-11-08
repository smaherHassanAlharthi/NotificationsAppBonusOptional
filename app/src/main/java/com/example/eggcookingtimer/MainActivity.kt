package com.example.eggcookingtimer

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button

class MainActivity : AppCompatActivity() {
    // declaring variables
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val notificationId = 1234
    private val channelId = "myapp.notifications"
    private val description = "Notification App Example"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countbtn = findViewById<Button>(R.id.btnStartCount)
        countbtn.setOnClickListener{

            notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val eggIntent = Intent(this,EggActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this,0,eggIntent,PendingIntent.FLAG_UPDATE_CURRENT)



            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel =
                    NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification
                    .Builder(this,channelId)
                    .setContentText("Ready")
                    .setContentTitle("Egg Cooking Counter")
                    .setSmallIcon(R.drawable.ic_stat_name)
                    .setContentIntent(pendingIntent)


            }else{
                builder = Notification
                    .Builder(this)
                    .setContentText("Ready")
                    .setContentTitle("Egg Cooking Counter")
                    .setSmallIcon(R.drawable.ic_stat_name)
                    .setContentIntent(pendingIntent)

            }

            val countDownTimer = object : CountDownTimer(5000,500) {


                override fun onTick(p0: Long) {

                }

                override fun onFinish() {
                    notificationManager.notify(notificationId,builder.build())
                }


            }
            countDownTimer.start()




        }



    }
}