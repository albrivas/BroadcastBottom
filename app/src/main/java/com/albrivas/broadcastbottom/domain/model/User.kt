package com.albrivas.broadcastbottom.domain.model

import java.util.*

data class User(
    val name: String?,
    val birthday: Date?,
    val phone: String?,
    val email: String?
)

fun User.toHasMap() = hashMapOf(
    "Nombre" to name,
    "Fecha de nacimiento" to birthday,
    "Teléfono" to phone,
    "Email" to email
)

