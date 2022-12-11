package com.albrivas.broadcastbottom.ui.login.choose

import androidx.activity.result.IntentSenderRequest
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albrivas.broadcastbottom.data.analytics.LoginTracking
import com.albrivas.broadcastbottom.usescases.login.GetGoogleIntentSenderUseCase
import com.albrivas.broadcastbottom.usescases.login.LoginFirebaseUseCase
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

class ChooseLoginViewModel(
    private val getGoogleIntentSenderUseCase: GetGoogleIntentSenderUseCase,
    private val loginFirebaseUseCase: LoginFirebaseUseCase,
    private val analytics: LoginTracking,
) : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

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
        state = state.copy(intentSenderRequest = intent)
    }

    private fun onError(exception: Exception) {
        state = state.copy(loginError = exception.message)
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