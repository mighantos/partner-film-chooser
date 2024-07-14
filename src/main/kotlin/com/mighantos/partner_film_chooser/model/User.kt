package com.mighantos.partner_film_chooser.model

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity(name = "user_app")
class User(
    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var firstName: String,

    @Column(nullable = false)
    var lastName: String,

    @Column(nullable = false)
    var password: String,
) : BaseEntity()