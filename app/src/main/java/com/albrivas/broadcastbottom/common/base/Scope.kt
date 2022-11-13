/*
 * File: Scope.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.common.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

interface Scope : CoroutineScope {

    class Impl : Scope {
        override lateinit var job: Job
    }

    var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun initScope() {
        job = SupervisorJob()
    }

    fun destroyScope() {
        job.cancel()
    }
}