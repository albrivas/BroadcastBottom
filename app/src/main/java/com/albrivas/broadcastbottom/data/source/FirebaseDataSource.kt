/*
 * File: FirebaseDataSource.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.data.source

import com.google.firebase.auth.AuthCredential

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