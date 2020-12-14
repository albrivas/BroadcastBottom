/*
 * File: LoginFacebookUseCase.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.usescases.login

import com.albrivas.broadcastbottom.data.repository.login.LoginFacebookRepository
import com.google.firebase.auth.AuthCredential

class LoginFacebookUseCase(
    private val loginFacebookRepository: LoginFacebookRepository
) {
    suspend fun invoke(credential: AuthCredential, result: (Boolean, Exception?) -> Unit) =
        loginFacebookRepository.loginFacebook(credential, result)
}