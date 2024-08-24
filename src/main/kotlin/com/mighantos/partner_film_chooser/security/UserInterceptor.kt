package com.mighantos.partner_film_chooser.security

import com.mighantos.partner_film_chooser.service.UserService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.util.*

@Component
class UserInterceptor(
    private val userService: UserService
) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val userId = SecurityContextHolder.getContext().authentication.name
        userService.create(UUID.fromString(userId))
        return true
    }
}
