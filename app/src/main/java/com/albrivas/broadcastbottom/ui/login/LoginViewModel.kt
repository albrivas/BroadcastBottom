package com.albrivas.broadcastbottom.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.albrivas.broadcastbottom.R
import com.albrivas.broadcastbottom.domain.model.FieldType
import com.albrivas.broadcastbottom.domain.model.ValidatorField
import com.albrivas.broadcastbottom.common.Event
import com.albrivas.broadcastbottom.common.base.BaseViewModel
import com.facebook.AccessToken
import com.google.firebase.auth.*
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {

    companion object {
        const val TAG = "TAG_LOGIN"
    }

    private val mAuth = FirebaseAuth.getInstance()

    var email: String? = null
    var password: String? = null
    var userName: String? = null

    sealed class UiModel: UiModelBase() {
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
                mAuth.signInWithEmailAndPassword(email!!, password!!)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            _model.value = UiModel.NavigateToHome
                        } else {
                            task.exception?.let { exception ->
                                _model.value = UiModel.ErrorLogin(exception)
                            }
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
            showLoading()
            launch {
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
        } else {
            _model.value = UiModel.ErrorFields(validate.second)
        }
    }

    fun forgotPassword() {
        val validate = validateForm(LoginType.FORGOT)

        if (validate.first) {
            showLoading()
            launch {
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

    fun signInWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        mAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _model.value = UiModel.NavigateToHome
            } else {
                task.exception?.let { exception ->
                    _model.value = UiModel.ErrorLogin(exception)
                }
            }
        }
    }

    fun signInWithFacebook(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)

        mAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _model.value = UiModel.NavigateToHome
            } else {
                task.exception?.let { exception ->
                    _model.value = UiModel.ErrorLogin(exception)
                }
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