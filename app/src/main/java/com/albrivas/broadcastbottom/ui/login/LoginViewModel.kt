package com.albrivas.broadcastbottom.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.albrivas.broadcastbottom.R
import com.albrivas.broadcastbottom.data.model.FieldType
import com.albrivas.broadcastbottom.data.model.ValidatorField
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
        class ErrorFields(val validatorField: ValidatorField) : UiModel()
    }

    fun signInWithUserAndPassword() {
        val validate = validateForm(LoginType.LOGIN)

        if (validate.first) {
            mAuth.signInWithEmailAndPassword(email!!, password!!).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _model.value = UiModel.NavigateCreateAccount(Event(TAG))
                } else {
                    task.exception?.let { exception ->
                        _model.value = UiModel.ErrorLogin(exception)
                    }
                }
            }
        } else {
            _model.value = UiModel.ErrorFields(validate.second)
        }
    }

    fun createAccount() {
        val validate = validateForm(LoginType.CREATE)

        if (validate.first) {
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
        } else {
            _model.value = UiModel.ErrorFields(validate.second)
        }
    }

    fun forgotPassword() {
        val validate = validateForm(LoginType.FORGOT)

        if (validate.first) {
            mAuth.sendPasswordResetEmail(email!!).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _model.value = UiModel.NavigateSignIn(Event(TAG))
                } else {
                    task.exception?.let { exception ->
                        _model.value = UiModel.ErrorLogin(exception)
                    }
                }
            }
        } else {
            _model.value = UiModel.ErrorFields(validate.second)
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