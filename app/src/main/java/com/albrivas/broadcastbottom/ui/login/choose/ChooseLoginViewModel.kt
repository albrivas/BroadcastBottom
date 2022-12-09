package com.albrivas.broadcastbottom.ui.login.choose

import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albrivas.broadcastbottom.data.analytics.LoginTracking
import com.albrivas.broadcastbottom.usescases.login.GetGoogleIntentSenderUseCase
import com.albrivas.broadcastbottom.usescases.login.LoginFirebaseUseCase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChooseLoginViewModel(
    private val getGoogleIntentSenderUseCase: GetGoogleIntentSenderUseCase,
    private val loginFirebaseUseCase: LoginFirebaseUseCase,
    private val analytics: LoginTracking,
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state

    fun prepareGoogleLogin() {
        trackerLogin()
        viewModelScope.launch {
            val response = getGoogleIntentSenderUseCase()
            response.fold(ifLeft = ::onError, ifRight = ::onSuccessIntent)
        }
    }

    fun login(token: String) {
        val credential = GoogleAuthProvider.getCredential(token, null)
        viewModelScope.launch {
            loginFirebaseUseCase(authCredential = credential)
        }
    }

    private fun onSuccessIntent(intent: IntentSenderRequest) {
        _state.update { it.copy(intentSenderRequest = intent) }
    }

    private fun onError(exception: Exception) {
        _state.update { it.copy(loginError = exception.message) }
    }

    private fun trackerLogin() {
        analytics.onSignGoogle()
    }

    fun initUiState(state: UiState) {
        UiState(loginError = state.loginError, intentSenderRequest = state.intentSenderRequest)
    }

    data class UiState(
        val intentSenderRequest: IntentSenderRequest? = null,
        val loginError: String? = null
    )
}