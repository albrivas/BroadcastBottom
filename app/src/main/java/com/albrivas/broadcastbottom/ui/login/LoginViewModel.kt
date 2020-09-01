package com.albrivas.broadcastbottom.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    companion object {
        const val TAG = "TAG_LOGIN"
    }

    var userName: String? = "albrivas95@outlook.es"
    var password: String? = "123456789"

    private lateinit var mAuth: FirebaseAuth

    init {
        val currentUser = mAuth.currentUser
        signInWithUserAndPassword()
    }

    fun signInWithUserAndPassword() {
        if (!userName.isNullOrEmpty() && !password.isNullOrEmpty()) {
            mAuth.signInWithEmailAndPassword(userName!!, password!!).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                }
            }
        }
    }

    fun signUpWithEmailAndPassword() {
        if (!userName.isNullOrEmpty() && !password.isNullOrEmpty()) {
            mAuth.createUserWithEmailAndPassword(userName!!, password!!).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signUpWithEmail:success")
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                }
            }
        }
    }
}