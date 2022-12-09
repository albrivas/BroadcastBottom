package com.albrivas.broadcastbottom.usescases.login

import arrow.core.Either
import com.albrivas.broadcastbottom.data.repository.login.LoginGoogleRepository
import com.google.firebase.auth.AuthCredential

class LoginFirebaseUseCase(
    private val loginGoogleRepository: LoginGoogleRepository
) {
    suspend operator fun invoke(authCredential: AuthCredential): Either<Exception, Unit> =
        loginGoogleRepository.loginFirebase(authCredential)
}