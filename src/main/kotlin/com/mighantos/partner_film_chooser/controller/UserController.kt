package com.mighantos.partner_film_chooser.controller

import com.mighantos.partner_film_chooser.dto.UserDetailsDto
import com.mighantos.partner_film_chooser.dto.UserDto
import com.mighantos.partner_film_chooser.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class UserController(val userService: UserService) {

    @GetMapping("/users/{id}")
    fun getUser(@PathVariable id: UUID): UserDetailsDto {
        return userService.getUser(id)
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody user: UserDto): UUID {
        return userService.createUser(user)
    }
}