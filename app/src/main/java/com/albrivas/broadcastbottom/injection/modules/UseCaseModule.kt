package com.albrivas.broadcastbottom.injection.modules

import com.albrivas.broadcastbottom.data.analytics.LoginTracking
import com.albrivas.broadcastbottom.ui.login.reset.ResetPasswordFragment
import com.albrivas.broadcastbottom.ui.login.signin.LoginFragment
import com.albrivas.broadcastbottom.ui.login.signup.SignUpFragment
import com.albrivas.broadcastbottom.ui.profile.ProfileFragment
import com.albrivas.broadcastbottom.usescases.UserDataUsesCase
import com.albrivas.broadcastbottom.usescases.login.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCaseModule = module {
    single { LoginFacebookUseCase(get()) }
    single { GetGoogleIntentSenderUseCase(get()) }
    single { LoginFirebaseUseCase(get()) }
    single { LoginUseCase(get()) }
    single { CreateAccountUseCase(get()) }
    single { ForgotPasswordUseCase(get()) }
    single { LoginTracking(get()) }
    single { UserDataUsesCase(get()) }
}