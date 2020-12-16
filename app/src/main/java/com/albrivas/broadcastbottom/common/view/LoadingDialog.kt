/*
 * File: LoadingDialog.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.common.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.albrivas.broadcastbottom.R
import com.albrivas.broadcastbottom.databinding.DialogLoadingBinding

class LoadingDialog(context: Context) :
    Dialog(context, R.style.FloatingDialog) {

    private lateinit var binding: DialogLoadingBinding

    init {
        setCancelable(false)
        setOnCancelListener(null)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    private val fadeInDuration: Long = 200
    private val fadeOutDuration: Long = 200

    private var isHiding: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )

        binding.viewBackground.startAnimation(
            AlphaAnimation(
                0f,
                binding.viewBackground.alpha
            ).apply {
                duration = fadeInDuration
            })
    }

    fun dismissWithAnimation() {
        if (!isHiding) {
            isHiding = true
            binding.viewBackground.startAnimation(
                AlphaAnimation(
                    binding.viewBackground.alpha,
                    0f
                ).apply {
                    duration = fadeOutDuration
                    setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationRepeat(animation: Animation?) {

                        }

                        override fun onAnimationEnd(animation: Animation?) {
                            super@LoadingDialog.dismiss()
                            isHiding = false
                        }

                        override fun onAnimationStart(animation: Animation?) {

                        }
                    })
                })
        }
    }
}