package com.albrivas.broadcastbottom.injection.modules

import com.albrivas.broadcastbottom.ui.base.MainViewModel
import com.albrivas.broadcastbottom.ui.home.HomeViewModel
import com.albrivas.broadcastbottom.ui.login.choose.ChooseLoginViewModel
import com.albrivas.broadcastbottom.ui.login.viewmodel.LoginViewModel
import com.albrivas.broadcastbottom.ui.notifications.NotificationViewModel
import com.albrivas.broadcastbottom.ui.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ChooseLoginViewModel(get(), get(), get()) }
    viewModel { LoginViewModel(get(), get(), get(), get(), get(), get(), get()) }
    viewModel { HomeViewModel() }
    viewModel { MainViewModel() }
    viewModel { NotificationViewModel() }
    viewModel { ProfileViewModel(get(), get(), get(), get()) }
}