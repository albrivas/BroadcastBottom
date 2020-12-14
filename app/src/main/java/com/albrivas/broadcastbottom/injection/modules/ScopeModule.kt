/*
 * File: ScopeModule.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.injection.modules

import com.albrivas.broadcastbottom.ui.base.MainActivity
import com.albrivas.broadcastbottom.ui.base.MainViewModel
import com.albrivas.broadcastbottom.ui.home.HomeFragment
import com.albrivas.broadcastbottom.ui.home.HomeViewModel
import com.albrivas.broadcastbottom.ui.login.choose.ChooseLoginFragment
import com.albrivas.broadcastbottom.ui.login.reset.ResetPasswordFragment
import com.albrivas.broadcastbottom.ui.login.signin.LoginFragment
import com.albrivas.broadcastbottom.ui.login.signup.SignUpFragment
import com.albrivas.broadcastbottom.ui.login.viewmodel.LoginViewModel
import com.albrivas.broadcastbottom.ui.notifications.NotificationFragment
import com.albrivas.broadcastbottom.ui.notifications.NotificationViewModel
import com.albrivas.broadcastbottom.ui.profile.ProfileFragment
import com.albrivas.broadcastbottom.ui.profile.ProfileViewModel
import com.albrivas.broadcastbottom.usescases.UserDataUsesCase
import com.albrivas.broadcastbottom.usescases.login.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val scopeModule = module {

    scope(named<ChooseLoginFragment>()) {
        viewModel { LoginViewModel(get(), get(), get(), get(), get(), get()) }
        scoped { LoginUseCase(get()) }
        scoped { CreateAccountUseCase(get()) }
        scoped { ForgotPasswordUseCase(get()) }
        scoped { LoginFacebookUseCase(get()) }
        scoped { LoginGoogleUseCase(get()) }
    }

    scope(named<LoginFragment>()) {
        viewModel { LoginViewModel(get(), get(), get(), get(), get(), get()) }
        scoped { LoginUseCase(get()) }
        scoped { CreateAccountUseCase(get()) }
        scoped { ForgotPasswordUseCase(get()) }
        scoped { LoginFacebookUseCase(get()) }
        scoped { LoginGoogleUseCase(get()) }
    }

    scope(named<SignUpFragment>()) {
        viewModel { LoginViewModel(get(), get(), get(), get(), get(), get()) }
        scoped { LoginUseCase(get()) }
        scoped { CreateAccountUseCase(get()) }
        scoped { ForgotPasswordUseCase(get()) }
        scoped { LoginFacebookUseCase(get()) }
        scoped { LoginGoogleUseCase(get()) }
    }

    scope(named<ResetPasswordFragment>()) {
        viewModel { LoginViewModel(get(), get(), get(), get(), get(), get()) }
        scoped { LoginUseCase(get()) }
        scoped { CreateAccountUseCase(get()) }
        scoped { ForgotPasswordUseCase(get()) }
        scoped { LoginFacebookUseCase(get()) }
        scoped { LoginGoogleUseCase(get()) }
    }

    scope(named<HomeFragment>()) {
        viewModel { HomeViewModel() }
    }

    scope(named<MainActivity>()) {
        viewModel { MainViewModel() }
    }

    scope(named<NotificationFragment>()) {
        viewModel { NotificationViewModel() }
    }

    scope(named<ProfileFragment>()) {
        viewModel { ProfileViewModel(get(), get(), get(), get()) }
        scoped { UserDataUsesCase(get()) }
    }
}