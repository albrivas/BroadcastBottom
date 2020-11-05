package com.albrivas.broadcastbottom.domain.model

data class ValidatorField(
    val errorMessage: Int,
    val fieldType: FieldType
)

enum class FieldType {
    EMAIL, PASSWORD, ACCOUNT, EMAIL_FORMATTED
}