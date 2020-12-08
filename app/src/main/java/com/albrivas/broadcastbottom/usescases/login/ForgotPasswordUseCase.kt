package com.albrivas.broadcastbottom.usescases.login

import com.albrivas.broadcastbottom.data.repository.login.ForgotPasswordRepository
import java.lang.Exception

class ForgotPasswordUseCase(
    private val forgotPasswordRepository: ForgotPasswordRepository
) {
    suspend fun invoke(
        email: String,
        result: (Boolean, Exception?) -> Unit
    ) = forgotPasswordRepository.forgotPassword(email, result)
}