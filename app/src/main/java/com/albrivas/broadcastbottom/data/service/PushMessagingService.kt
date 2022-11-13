/*
 * File: PushMessagingService.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.data.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.albrivas.broadcastbottom.R
import com.albrivas.broadcastbottom.data.receiver.PushReceiver
import com.albrivas.broadcastbottom.ui.base.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class PushMessagingService : FirebaseMessagingService() {

    companion object {
        const val ID_PUSH = 99
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage.notification != null) {
            sendBroadcast(Intent(this, PushReceiver::class.java).setAction("PUSH_NOTIFICATION"))
            showNotification(remoteMessage)
        }
    }

    private fun showNotification(remoteMessage: RemoteMessage) {

        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(
            this, ID_PUSH, intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        createNotification(
            pendingIntent,
            remoteMessage.notification?.title,
            remoteMessage.notification?.body,
            ID_PUSH
        )
    }

    override fun onNewToken(token: String) {
        Log.d("Device_Token", "Refreshed token: $token")
    }


    private fun createNotification(
        intent: PendingIntent,
        title: String?,
        body: String?,
        chanelId: Int
    ) {
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, chanelId.toString()).apply {
            setSmallIcon(R.drawable.ic_notifications)
            setContentTitle(title)
            setContentText(body)
            setAutoCancel(true)
            setSound(defaultSoundUri)
            setContentIntent(intent)
        }


        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                chanelId.toString(),
                "Channel albrivas",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(chanelId /* ID of notification */, notificationBuilder.build())
    }
}