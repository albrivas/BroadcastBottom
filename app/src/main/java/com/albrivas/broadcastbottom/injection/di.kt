package com.albrivas.broadcastbottom.injection

import android.app.Application
import com.albrivas.broadcastbottom.injection.modules.dataModule
import com.albrivas.broadcastbottom.injection.modules.scopeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

fun Application.initDI() {
    startKoin {
        androidLogger(Level.ERROR)
        androidContext(this@initDI)
        modules(listOf(scopeModule, dataModule))
    }
}

