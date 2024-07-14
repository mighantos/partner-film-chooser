package com.mighantos.partner_film_chooser.dto

import com.mighantos.partner_film_chooser.model.User

data class UserDetailsDto(
    val email: String,
    val firstName: String,
    val lastName: String,
) {
    constructor(user: User) : this(
        email = user.email,
        firstName = user.firstName,
        lastName = user.lastName,
    )
}