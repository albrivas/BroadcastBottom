package com.albrivas.broadcastbottom.injection.modules

import com.albrivas.broadcastbottom.data.repository.UploadImageRepository
import org.koin.dsl.module

val dataModule = module {
    factory { UploadImageRepository(get()) }
}