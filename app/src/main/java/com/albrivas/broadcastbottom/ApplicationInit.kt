package com.albrivas.broadcastbottom

import android.app.Application
import com.albrivas.broadcastbottom.injection.initDI

class ApplicationInit: Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}