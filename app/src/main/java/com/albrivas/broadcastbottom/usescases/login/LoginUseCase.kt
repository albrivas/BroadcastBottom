/*
 * File: LoginUseCase.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.usescases.login

import com.albrivas.broadcastbottom.data.repository.login.LoginRepository
import java.lang.Exception

class LoginUseCase(
    private val loginRepository: LoginRepository
) {
    suspend fun invoke(email: String, password: String, result: (Boolean, Exception?) -> Unit) =
        loginRepository.loginWithEmailAndPassword(email, password, result)
}