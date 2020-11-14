package com.albrivas.broadcastbottom.injection.modules

import com.albrivas.broadcastbottom.ui.base.MainActivity
import com.albrivas.broadcastbottom.ui.base.MainViewModel
import com.albrivas.broadcastbottom.ui.home.HomeFragment
import com.albrivas.broadcastbottom.ui.home.HomeViewModel
import com.albrivas.broadcastbottom.ui.login.*
import com.albrivas.broadcastbottom.ui.notifications.NotificationFragment
import com.albrivas.broadcastbottom.ui.notifications.NotificationViewModel
import com.albrivas.broadcastbottom.ui.profile.ProfileFragment
import com.albrivas.broadcastbottom.ui.profile.ProfileViewModel
import com.albrivas.broadcastbottom.usescases.UserDataUC
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val scopeModule = module {

    scope(named<ChooseLoginFragment>()) {
        viewModel { LoginViewModel() }
    }

    scope(named<LoginFragment>()) {
        viewModel { LoginViewModel() }
    }

    scope(named<SignUpFragment>()) {
        viewModel { LoginViewModel() }
    }

    scope(named<ResetPasswordFragment>()) {
        viewModel { LoginViewModel() }
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
        viewModel { ProfileViewModel(get(), get(), get()) }
        scoped { UserDataUC(get()) }
    }
}