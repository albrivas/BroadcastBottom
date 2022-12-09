package com.albrivas.broadcastbottom.data.source

import androidx.activity.result.IntentSenderRequest
import arrow.core.Either
import com.google.firebase.auth.AuthCredential

interface FirebaseDataSource {
    fun loginWithEmailAndPassword(
        email: String,
        password: String,
        result: (Boolean, Exception?) -> Unit
    )

    fun createAccount(
        email: String,
        password: String,
        result: (Boolean, Exception?) -> Unit
    )

    fun forgotPassword(
        email: String,
        result: (Boolean, Exception?) -> Unit
    )

    fun loginFacebook(
        credential: AuthCredential,
        result: (Boolean, Exception?) -> Unit
    )

    suspend fun loginFirebase(
        credential: AuthCredential
    ): Either<Exception, Unit>

    suspend fun oneTapSignInWithGoogle(): Either<Exception, IntentSenderRequest>

    suspend fun signUpWithGoogle(): Either<Exception, IntentSenderRequest>
}