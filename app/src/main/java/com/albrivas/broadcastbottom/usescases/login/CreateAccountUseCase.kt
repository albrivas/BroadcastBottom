package com.albrivas.broadcastbottom.usescases.login

import com.albrivas.broadcastbottom.data.repository.login.CreateAccountRepository
import java.lang.Exception

class CreateAccountUseCase(
    private val createAccountRepository: CreateAccountRepository
) {
    suspend fun invoke(
        email: String,
        password: String,
        result: (Boolean, Exception?) -> Unit
    ) = createAccountRepository.createAccount(email, password, result)
}