/*
 * File: Extensions.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.common

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import com.albrivas.broadcastbottom.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun Context.toast(message: String) {
    Toast.makeText(
        this,
        message,
        Toast.LENGTH_SHORT
    ).show()
}

fun AppCompatImageView.loadLocalUrl(drawable: Int) {
    Glide.with(context).load(drawable)
        .apply(RequestOptions().circleCrop())
        .into(this)
}

fun AppCompatImageView.loadUrl(uri: Uri) {
    Glide.with(context).load(uri)
        .apply(RequestOptions().circleCrop().placeholder(R.drawable.ic_person))
        .into(this)
}