package com.albrivas.broadcastbottom.injection.modules

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.koin.dsl.module

val appModule = module {
    single { FirebaseStorage.getInstance().reference }
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
}