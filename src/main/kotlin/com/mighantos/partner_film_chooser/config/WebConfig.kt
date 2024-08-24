package com.mighantos.partner_film_chooser.config

import com.mighantos.partner_film_chooser.security.UserInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(val userInterceptor: UserInterceptor) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(userInterceptor)
    }
}