package com.albrivas.broadcastbottom.data.repository.login

import com.albrivas.broadcastbottom.data.datasource.FirebaseDataSource
import java.lang.Exception

class CreateAccountRepository(
    private val firebaseDataSource: FirebaseDataSource
) {
    fun createAccount(
        email: String,
        password: String,
        result: (Boolean, Exception?) -> Unit
    ) = firebaseDataSource.createAccount(email, password, result)
}