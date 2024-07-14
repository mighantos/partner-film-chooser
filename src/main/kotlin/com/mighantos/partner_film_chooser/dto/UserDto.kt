package com.mighantos.partner_film_chooser.dto

import com.mighantos.partner_film_chooser.model.User

data class UserDto(
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
) {
    fun toUser(): User{
        return User(email, firstName, lastName, password)
    }
}