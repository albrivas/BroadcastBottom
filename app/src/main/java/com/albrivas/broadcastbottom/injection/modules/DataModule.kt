package com.albrivas.broadcastbottom.injection.modules

import com.albrivas.broadcastbottom.data.repository.UserDataRepository
import org.koin.dsl.module

val dataModule = module {
    factory { UserDataRepository(get(), get()) }
}