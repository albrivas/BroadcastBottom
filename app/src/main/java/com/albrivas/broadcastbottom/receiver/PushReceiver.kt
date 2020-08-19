package com.albrivas.broadcastbottom.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class PushReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.sendBroadcast(Intent("PUSH_RECEIVED"))
    }
}
