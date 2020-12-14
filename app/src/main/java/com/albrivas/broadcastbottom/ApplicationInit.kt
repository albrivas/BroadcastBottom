/*
 * File: ApplicationInit.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom

import android.app.Application
import com.albrivas.broadcastbottom.injection.initDI

class ApplicationInit: Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}