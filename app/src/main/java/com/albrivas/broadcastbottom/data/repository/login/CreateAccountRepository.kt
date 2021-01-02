/*
 * File: CreateAccountRepository.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.data.repository.login

import com.albrivas.broadcastbottom.data.source.FirebaseDataSource
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