/*
 * File: Either.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.common

sealed class Either<out L, out R> {

    data class Left<out L>(val l: L) : Either<L, Nothing>()

    data class Right<out R>(val r: R) : Either<Nothing, R>()

}