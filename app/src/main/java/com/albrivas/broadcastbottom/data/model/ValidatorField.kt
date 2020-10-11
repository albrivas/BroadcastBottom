package com.albrivas.broadcastbottom.data.model

data class ValidatorField(
    val errorMessage: Int,
    val fieldType: FieldType
)

enum class FieldType {
    EMAIL, PASSWORD, ACCOUNT, EMAIL_FORMATTED
}