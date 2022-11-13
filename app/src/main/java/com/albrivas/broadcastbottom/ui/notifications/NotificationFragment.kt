/*
 * File: NotificationFragment.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.albrivas.broadcastbottom.databinding.FragmentNotificationBinding
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding
    private val viewModel: NotificationViewModel by lifecycleScope.viewModel(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}