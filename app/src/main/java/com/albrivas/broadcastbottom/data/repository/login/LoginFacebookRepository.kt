package com.albrivas.broadcastbottom.data.repository.login

import com.albrivas.broadcastbottom.data.datasource.FirebaseDataSource
import com.google.firebase.auth.AuthCredential
import java.lang.Exception

class LoginFacebookRepository(
    private val firebaseDataSource: FirebaseDataSource
) {
    suspend fun loginFacebook(credential: AuthCredential, result: (Boolean, Exception?) -> Unit) =
        firebaseDataSource.loginFacebook(credential, result)
}