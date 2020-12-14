/*
 * File: AppModule.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.injection.modules

import com.albrivas.broadcastbottom.data.datasource.FirebaseDataSource
import com.albrivas.broadcastbottom.data.datasource.LoginDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.koin.dsl.module

val appModule = module {
    single { FirebaseStorage.getInstance().reference }
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    factory<FirebaseDataSource> { LoginDataSource(get()) }
}