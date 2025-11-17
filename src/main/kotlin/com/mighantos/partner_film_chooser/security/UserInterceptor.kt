package com.mighantos.partner_film_chooser.security

import com.mighantos.partner_film_chooser.model.User
import com.mighantos.partner_film_chooser.service.UserService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.util.*

@Component
class UserInterceptor(
    private val userService: UserService,
) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val authentication = SecurityContextHolder.getContext().authentication
        val userId = authentication.name
        val claims = (authentication.credentials as Jwt).claims
        val userName = claims["preferred_username"] as String
        val firstName = claims["given_name"] as String
        val lastName = claims["family_name"] as String
        val user = User(UUID.fromString(userId), userName, firstName, lastName)
        userService.save(user)
        return true
    }
}
