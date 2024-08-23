package com.mighantos.partner_film_chooser.dto

import com.mighantos.partner_film_chooser.model.User
import java.util.*

data class UserDto(
    val id: UUID
) {
    fun toUser(): User {
        return User(id)
    }
}