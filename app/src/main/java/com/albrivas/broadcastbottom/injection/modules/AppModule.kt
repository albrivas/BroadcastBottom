

package com.albrivas.broadcastbottom.injection.modules

import com.albrivas.broadcastbottom.BuildConfig
import com.albrivas.broadcastbottom.data.source.FirebaseDataSourceImp
import com.albrivas.broadcastbottom.data.source.FirebaseDataSource
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
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
    single { Identity.getSignInClient(androidContext()) }
    factory<FirebaseDataSource> { FirebaseDataSourceImp(get(), get()) }
}

/**
 * Provide request for make sign IN with Google
 */
private val provideSignInRequest = BeginSignInRequest.builder()
    .setGoogleIdTokenRequestOptions(
        BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
            .setSupported(true)
            .setServerClientId(BuildConfig.WEB_CLIENT_ID)
            .setFilterByAuthorizedAccounts(true)
            .build())
    .build()

/**
 * Provide request for make sign UP with Google
 */
private val provideSignUpRequest = BeginSignInRequest.builder()
    .setGoogleIdTokenRequestOptions(
        BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
            .setSupported(true)
            .setServerClientId(BuildConfig.WEB_CLIENT_ID)
            .setFilterByAuthorizedAccounts(false)
            .build())
    .build()