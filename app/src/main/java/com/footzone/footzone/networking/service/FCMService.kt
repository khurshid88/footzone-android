package com.footzone.footzone.networking.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.footzone.footzone.R
import com.footzone.footzone.ui.activity.MainActivity
import com.footzone.footzone.utils.KeyValues
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.random.Random

class FCMService : FirebaseMessagingService() {

    private val CHANNEL_ID: String = "Something"
    val TAG = FCMService::class.java.simpleName

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i(TAG, "Refreshed Token:: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.i(TAG, "Message: ${message.data}")
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(KeyValues.NOTIFICATION_TITLE, message.data["title"])
        val requestCode = (0..10).random()
        val pendingIntent = PendingIntent.getActivity(this, requestCode, intent, FLAG_ONE_SHOT)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(message.data["title"])
            .setContentText(message.data["body"])
            .setSmallIcon(R.drawable.ic_notification_home)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_product_logo))
            .setStyle(
                NotificationCompat.BigTextStyle().bigText(message.data["body"])
                    .setBigContentTitle(message.data["title"])
            )
            .setAutoCancel(true)
            .build()

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            createNotificationChannel(manager)
        val notificationId = Random.nextInt()
        manager.notify(notificationId, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(manager: NotificationManager) {
        val channelName = "channelName"
        val channel = NotificationChannel(CHANNEL_ID, channelName, IMPORTANCE_HIGH).apply {
            description = "My channel description"
            enableLights(true)
            lightColor = Color.BLUE
        }
        manager.createNotificationChannel(channel)
    }
}