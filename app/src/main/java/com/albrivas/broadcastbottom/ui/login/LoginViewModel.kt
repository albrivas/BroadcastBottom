package com.albrivas.broadcastbottom.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    companion object {
        const val TAG = "TAG_LOGIN"
    }

    var email: String? = "albrivas95@outlook.es"
    var password: String? = "123456789"

    private val mAuth = FirebaseAuth.getInstance()

    init {
        val currentUser = mAuth.currentUser
    }


    fun signInWithUserAndPassword() {
        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            mAuth.signInWithEmailAndPassword(email!!, password!!).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                }
            }
        }
    }

    fun createAccount() {
        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            mAuth.createUserWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signUpWithEmail:success")
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                    }
                }
        }
    }
}