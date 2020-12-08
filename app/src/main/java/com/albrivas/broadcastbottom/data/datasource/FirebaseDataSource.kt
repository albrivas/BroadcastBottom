package com.albrivas.broadcastbottom.data.datasource

import com.google.firebase.auth.AuthCredential
import java.lang.Exception

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

    fun loginGoogle(
        credential: AuthCredential,
        result: (Boolean, Exception?) -> Unit
    )
}