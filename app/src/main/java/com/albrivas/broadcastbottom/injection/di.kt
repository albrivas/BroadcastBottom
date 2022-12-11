package com.albrivas.broadcastbottom.injection

import android.app.Application
import com.albrivas.broadcastbottom.injection.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

fun Application.initDI() {
    startKoin {
        androidLogger(Level.ERROR)
        androidContext(this@initDI)
        modules(listOf(appModule, useCaseModule, dataModule, viewModelModule))
    }
}

