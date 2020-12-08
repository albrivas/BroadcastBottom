package com.albrivas.broadcastbottom.usescases.login

import com.albrivas.broadcastbottom.data.repository.login.LoginFacebookRepository
import com.google.firebase.auth.AuthCredential

class LoginFacebookUseCase(
    private val loginFacebookRepository: LoginFacebookRepository
) {
    suspend fun invoke(credential: AuthCredential, result: (Boolean, Exception?) -> Unit) =
        loginFacebookRepository.loginFacebook(credential, result)
}