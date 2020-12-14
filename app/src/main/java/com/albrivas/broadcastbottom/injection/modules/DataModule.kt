/*
 * File: DataModule.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.injection.modules

import com.albrivas.broadcastbottom.data.repository.UserDataRepository
import com.albrivas.broadcastbottom.data.repository.login.*
import org.koin.dsl.module

val dataModule = module {
    factory { UserDataRepository(get(), get()) }
    factory { LoginRepository(get()) }
    factory { CreateAccountRepository(get()) }
    factory { ForgotPasswordRepository(get()) }
    factory { LoginGoogleRepository(get()) }
    factory { LoginFacebookRepository(get()) }
}