/*
 * File: AppModule.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.injection.modules

import com.albrivas.broadcastbottom.data.server.LoginDataSource
import com.albrivas.broadcastbottom.data.source.FirebaseDataSource
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { FirebaseStorage.getInstance().reference }
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FirebaseAnalytics.getInstance(androidContext()) }
    factory<FirebaseDataSource> { LoginDataSource(get()) }
}