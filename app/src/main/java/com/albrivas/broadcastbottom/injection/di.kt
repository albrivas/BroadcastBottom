/*
 * File: di.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.injection

import android.app.Application
import com.albrivas.broadcastbottom.injection.modules.appModule
import com.albrivas.broadcastbottom.injection.modules.dataModule
import com.albrivas.broadcastbottom.injection.modules.scopeModule
import com.google.firebase.storage.FirebaseStorage
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger(Level.ERROR)
        androidContext(this@initDI)
        modules(listOf(appModule, scopeModule, dataModule))
    }
}

