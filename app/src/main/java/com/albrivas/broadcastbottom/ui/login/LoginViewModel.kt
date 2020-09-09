package com.albrivas.broadcastbottom.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.albrivas.broadcastbottom.ui.common.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest

class LoginViewModel : ViewModel() {

    companion object {
        const val TAG = "TAG_LOGIN"
    }

    private val mAuth = FirebaseAuth.getInstance()

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel> get() = _model

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    var email: String? = null
    var password: String? = null
    var userName: String? = null

    sealed class UiModel {
        class NavigateCreateAccount(val event: Event<String>) : UiModel()
        class NavigateSignIn(val event: Event<String>) : UiModel()
        class NavigateResetPassword(val event: Event<String>) : UiModel()
        class ErrorLogin(val exception: Exception) : UiModel()
    }


    fun signInWithUserAndPassword() {
        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            mAuth.signInWithEmailAndPassword(email!!, password!!).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _model.value = UiModel.NavigateCreateAccount(Event(TAG))
                } else {
                    task.exception?.let { exception ->
                        _model.value = UiModel.ErrorLogin(exception)
                    }
                }
            }
        }
    }

    fun createAccount() {
        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            mAuth.createUserWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        updateUserProfile(mAuth.currentUser)
                        _model.value = UiModel.NavigateSignIn(Event(TAG))
                    } else {
                        task.exception?.let { exception ->
                            _model.value = UiModel.ErrorLogin(exception)
                        }
                    }
                }
        }
    }

    fun forgotPassword() {
        if (!email.isNullOrEmpty()) {
            mAuth.sendPasswordResetEmail(email!!).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _model.value = UiModel.NavigateSignIn(Event(TAG))
                } else {
                    task.exception?.let { exception ->
                        _model.value = UiModel.ErrorLogin(exception)
                    }
                }
            }
        }
    }

    private fun updateUserProfile(currentUser: FirebaseUser?) {
        currentUser?.let { user ->
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(userName!!)
                .build()

            user.updateProfile(profileUpdates)
        }
    }

    fun navigateToLogin() {
        _model.value = UiModel.NavigateSignIn(Event(TAG))
    }

    fun navigateToSignUp() {
        _model.value = UiModel.NavigateCreateAccount(Event(TAG))
    }

    fun navigateToResetPassword() {
        _model.value = UiModel.NavigateResetPassword(Event(TAG))
    }
}