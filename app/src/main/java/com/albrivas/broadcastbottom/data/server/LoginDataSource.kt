/*
 * File: LoginDataSource.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 17/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.data.server

import com.albrivas.broadcastbottom.data.source.FirebaseDataSource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth

class LoginDataSource(
    private val mAuth: FirebaseAuth
) : FirebaseDataSource {
    override fun loginWithEmailAndPassword(
        email: String,
        password: String,
        result: (Boolean, Exception?) -> Unit
    ) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful)
                result(true, null)
            else
                result(false, task.exception)
        }

    }

    override fun createAccount(
        email: String,
        password: String,
        result: (Boolean, Exception?) -> Unit
    ) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful)
                    result(true, null)
                else
                    result(false, task.exception)
            }
    }

    override fun forgotPassword(
        email: String,
        result: (Boolean, Exception?) -> Unit
    ) {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful)
                result(true, null)
            else
                result(false, task.exception)

        }
    }

    override fun loginFacebook(credential: AuthCredential, result: (Boolean, Exception?) -> Unit) {
        mAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful)
                result(true, null)
            else
                result(false, task.exception)
        }
    }

    override fun loginGoogle(credential: AuthCredential, result: (Boolean, Exception?) -> Unit) {
        mAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful)
                result(true, null)
            else
                result(false, task.exception)
        }
    }
}