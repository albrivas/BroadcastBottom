package com.albrivas.broadcastbottom.data.repository.login

import com.albrivas.broadcastbottom.data.datasource.FirebaseDataSource
import java.lang.Exception

class ForgotPasswordRepository(
    private val firebaseDataSource: FirebaseDataSource
) {
    suspend fun forgotPassword(
        email: String,
        result: (Boolean, Exception?) -> Unit
    ) = firebaseDataSource.forgotPassword(email, result)
}