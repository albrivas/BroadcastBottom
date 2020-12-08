package com.albrivas.broadcastbottom.usescases.login

import com.albrivas.broadcastbottom.data.repository.login.LoginGoogleRepository
import com.google.firebase.auth.AuthCredential

class LoginGoogleUseCase(
    private val loginGoogleRepository: LoginGoogleRepository
) {
    suspend fun invoke(credential: AuthCredential, result: (Boolean, Exception?) -> Unit) =
        loginGoogleRepository.loginGoogle(credential, result)
}