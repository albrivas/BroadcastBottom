package com.albrivas.broadcastbottom.data.repository.login

import com.albrivas.broadcastbottom.data.datasource.FirebaseDataSource
import com.google.firebase.auth.AuthCredential
import java.lang.Exception

class LoginGoogleRepository(
    private val firebaseDataSource: FirebaseDataSource
) {
    suspend fun loginGoogle(credential: AuthCredential, result: (Boolean, Exception?) -> Unit) =
        firebaseDataSource.loginGoogle(credential, result)
}