/*
 * File: ValidatorField.kt
 * Project: BroadcastBottom
 *
 * Created by albrivas on 14/12/2020
 * Copyright © 2019 Alberto Rivas. All rights reserved.
 */

package com.albrivas.broadcastbottom.domain.model

data class ValidatorField(
    val errorMessage: Int,
    val fieldType: FieldType
)

enum class FieldType {
    EMAIL, PASSWORD, ACCOUNT, EMAIL_FORMATTED
}