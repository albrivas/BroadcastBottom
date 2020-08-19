package com.albrivas.broadcastbottom

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.albrivas.broadcastbottom.databinding.ActivityMainBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var countNotification = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, container, false)
        setContentView(binding.root)
        getTokenFirebase()
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(broadcastReceiver, IntentFilter("PUSH_RECEIVED"))
    }

    private var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            addBadgeNotification()
        }
    }

    private fun addBadgeNotification() {
        countNotification++
       val badge =  binding.bottomNavigation.getOrCreateBadge(R.id.navigation_alerts)
        badge.isVisible = true
        badge.number = countNotification
    }

    private fun getTokenFirebase() {
        FirebaseInstanceId.getInstance()
            .instanceId.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful)
                    return@OnCompleteListener

                Log.d("Device_Token", task.result?.token)
            })
    }
}