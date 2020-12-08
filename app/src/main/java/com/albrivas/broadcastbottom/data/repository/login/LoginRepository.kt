package com.albrivas.broadcastbottom.data.repository.login

import com.albrivas.broadcastbottom.data.datasource.FirebaseDataSource
import com.google.firebase.auth.AuthCredential
import java.lang.Exception

class LoginRepository(
    private val firebaseDataSource: FirebaseDataSource
) {
    suspend fun loginWithEmailAndPassword(
        email: String,
        password: String,
        result: (Boolean, Exception?) -> Unit
    ) = firebaseDataSource.loginWithEmailAndPassword(email, password, result)
}