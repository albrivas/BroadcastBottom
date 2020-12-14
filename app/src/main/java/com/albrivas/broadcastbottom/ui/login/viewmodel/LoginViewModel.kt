/*
 * File: LoginViewModel.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.ui.login.viewmodel

import android.util.Patterns
import com.albrivas.broadcastbottom.R
import com.albrivas.broadcastbottom.domain.model.FieldType
import com.albrivas.broadcastbottom.domain.model.ValidatorField
import com.albrivas.broadcastbottom.common.Event
import com.albrivas.broadcastbottom.common.base.BaseViewModel
import com.albrivas.broadcastbottom.usescases.login.*
import com.facebook.AccessToken
import com.google.firebase.auth.*
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val createAccountUseCase: CreateAccountUseCase,
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val loginFacebookUseCase: LoginFacebookUseCase,
    private val loginGoogleUseCase: LoginGoogleUseCase,
    private val mAuth: FirebaseAuth
) : BaseViewModel() {

    companion object {
        const val TAG = "TAG_LOGIN"
    }

    var email: String? = null
    var password: String? = null
    var userName: String? = null

    sealed class UiModel : UiModelBase() {
        class NavigateCreateAccount(val event: Event<String>) : UiModel()
        class NavigateSignIn(val event: Event<String>) : UiModel()
        class NavigateResetPassword(val event: Event<String>) : UiModel()
        object NavigateToHome : UiModel()
        class ErrorLogin(val exception: Exception) : UiModel()
        class ErrorFields(val validatorField: ValidatorField) : UiModel()
    }

    init {
        if (mAuth.currentUser != null)
            _model.value = UiModel.NavigateToHome
    }

    fun signInWithUserAndPassword() {
        val validate = validateForm(LoginType.LOGIN)

        if (validate.first) {
            showLoading()
            launch {
                loginUseCase.invoke(email!!, password!!) { isSuccessful, exception ->
                    if (isSuccessful)
                        _model.value = UiModel.NavigateToHome
                    else
                        exception?.let { _model.value = UiModel.ErrorLogin(it) }
                }
            }
        } else {
            _model.value = UiModel.ErrorFields(validate.second)
        }
    }

    fun createAccount() {
        val validate = validateForm(LoginType.CREATE)

        if (validate.first) {
            showLoading()
            launch {
                createAccountUseCase.invoke(email!!, password!!) { isSuccessful, exception ->
                    if (isSuccessful) {
                        updateUserProfile(mAuth.currentUser)
                        _model.value = UiModel.NavigateSignIn(Event(TAG))
                    } else
                        exception?.let { _model.value = UiModel.ErrorLogin(it) }
                }
            }
        } else {
            _model.value = UiModel.ErrorFields(validate.second)
        }
    }

    fun forgotPassword() {
        val validate = validateForm(LoginType.FORGOT)

        if (validate.first) {
            showLoading()
            launch {
                forgotPasswordUseCase.invoke(email!!) { isSuccessful, exception ->
                    if (isSuccessful)
                        _model.value = UiModel.NavigateSignIn(Event(TAG))
                    else
                        exception?.let { _model.value = UiModel.ErrorLogin(exception) }
                }
            }
        } else {
            _model.value = UiModel.ErrorFields(validate.second)
        }
    }

    fun signInWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        launch {
            loginGoogleUseCase.invoke(credential) { isSuccessful, exception ->
                if (isSuccessful)
                    _model.value = UiModel.NavigateToHome
                else
                    exception?.let { _model.value = UiModel.ErrorLogin(exception) }
            }
        }
    }

    fun signInWithFacebook(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)

        launch {
            loginFacebookUseCase.invoke(credential) { isSuccessful, exception ->
                if (isSuccessful)
                    _model.value = UiModel.NavigateToHome
                else
                    exception?.let { _model.value = UiModel.ErrorLogin(exception) }
            }
        }
    }

    private fun validateForm(type: LoginType): Pair<Boolean, ValidatorField> {
        var isCorrect = false
        var error = 0
        var typeField = FieldType.EMAIL

        when (type) {
            LoginType.LOGIN -> {
                when {
                    email.isNullOrEmpty() -> {
                        error = R.string.error_field
                        typeField = FieldType.EMAIL
                    }
                    password.isNullOrEmpty() -> {
                        error = R.string.error_field
                        typeField = FieldType.PASSWORD
                    }
                    !Patterns.EMAIL_ADDRESS.matcher(email!!).matches() -> {
                        error = R.string.error_email
                        typeField = FieldType.EMAIL_FORMATTED
                    }
                    else -> isCorrect = true
                }
            }
            LoginType.CREATE -> {
                when {
                    userName.isNullOrEmpty() -> {
                        error = R.string.error_field
                        typeField = FieldType.ACCOUNT
                    }
                    email.isNullOrEmpty() -> {
                        error = R.string.error_field
                        typeField = FieldType.EMAIL
                    }
                    password.isNullOrEmpty() -> {
                        error = R.string.error_field
                        typeField = FieldType.PASSWORD
                    }
                    !Patterns.EMAIL_ADDRESS.matcher(email!!).matches() -> {
                        error = R.string.error_email
                        typeField = FieldType.EMAIL_FORMATTED
                    }
                    else -> isCorrect = true
                }
            }
            LoginType.FORGOT -> {
                when {
                    email.isNullOrEmpty() -> {
                        error = R.string.error_field
                        typeField = FieldType.EMAIL
                    }
                    !Patterns.EMAIL_ADDRESS.matcher(email!!).matches() -> {
                        error = R.string.error_email
                        typeField = FieldType.EMAIL_FORMATTED
                    }
                    else -> isCorrect = true
                }
            }
        }

        val validator = ValidatorField(error, typeField)

        return Pair(isCorrect, validator)
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

    private enum class LoginType {
        LOGIN, CREATE, FORGOT
    }
}