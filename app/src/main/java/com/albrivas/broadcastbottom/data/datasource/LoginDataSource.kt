package com.albrivas.broadcastbottom.data.datasource

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

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