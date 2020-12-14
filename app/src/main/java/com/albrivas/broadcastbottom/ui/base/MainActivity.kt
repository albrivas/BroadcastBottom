/*
 * File: MainActivity.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.ui.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.albrivas.broadcastbottom.R
import com.albrivas.broadcastbottom.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel: MainViewModel by lifecycleScope.viewModel(this)
    private var countNotification = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, container, false)
        setContentView(binding.root)
        configureToolbar()
        instances()
        configureNavigation()
        observers()
    }

    private fun configureToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayShowHomeEnabled(false)
            setDisplayHomeAsUpEnabled(false)
        }
    }

    private fun instances() {
        binding.apply {
            viewmodel = viewModel
            lifecycleOwner = this@MainActivity
        }
    }

    private fun configureNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_alerts,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener(this)
    }

    private fun observers() {
        viewModel.model.observe(this, Observer(::updateUi))
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(broadcastReceiver, IntentFilter("PUSH_RECEIVED"))
    }

    private fun updateUi(model: MainViewModel.UiModel) {
        when (model) {
            is MainViewModel.UiModel.DeleteNotifications -> removeBadge()
        }
    }

    private var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            addBadgeNotification()
        }
    }

    private fun addBadgeNotification() {
        countNotification++
        val badge = binding.bottomNavigation.getOrCreateBadge(R.id.navigation_alerts)
        badge.isVisible = true
        badge.number = countNotification
    }

    private fun removeBadge() {
        countNotification = 0
        binding.bottomNavigation.removeBadge(R.id.navigation_alerts)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {
            R.id.navigation_alerts ->
                binding.iconDelete.visibility =
                    View.VISIBLE
            else ->
                binding.iconDelete.visibility =
                    View.GONE
        }
    }
}