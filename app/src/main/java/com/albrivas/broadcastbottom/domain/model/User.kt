package com.albrivas.broadcastbottom.domain.model

data class User(
    val email: String?,
    val birthday: String?,
    val name: String?,
    val phone: String?
)

fun User.toHasMap() = hashMapOf(
    "Email" to email,
    "Fecha de nacimiento" to birthday,
    "Nombre" to name,
    "Teléfono" to phone
)

fun Map<String, Any?>.toUser(): User {
    val orderBy = toSortedMap()
    val values = orderBy.values.toList()

    return User("${values[0]}", "${values[1]}", "${values[2]}", "${values[3]}")
}

