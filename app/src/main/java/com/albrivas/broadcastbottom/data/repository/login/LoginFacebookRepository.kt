/*
 * File: LoginFacebookRepository.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.data.repository.login

import com.albrivas.broadcastbottom.data.source.FirebaseDataSource
import com.google.firebase.auth.AuthCredential
import java.lang.Exception

class LoginFacebookRepository(
    private val firebaseDataSource: FirebaseDataSource
) {
    suspend fun loginFacebook(credential: AuthCredential, result: (Boolean, Exception?) -> Unit) =
        firebaseDataSource.loginFacebook(credential, result)
}