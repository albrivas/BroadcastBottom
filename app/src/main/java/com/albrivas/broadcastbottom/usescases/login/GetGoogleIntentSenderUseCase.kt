package com.albrivas.broadcastbottom.usescases.login

import androidx.activity.result.IntentSenderRequest
import arrow.core.Either
import com.albrivas.broadcastbottom.data.repository.login.LoginGoogleRepository

class GetGoogleIntentSenderUseCase(
    private val loginGoogleRepository: LoginGoogleRepository
) {
    suspend operator fun invoke(): Either<Exception, IntentSenderRequest> =
        loginGoogleRepository.getIntentLoginGoogle()
}