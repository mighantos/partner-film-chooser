package com.mighantos.partner_film_chooser.config

import com.mighantos.partner_film_chooser.config.properties.KeycloakConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(KeycloakConfigurationProperties::class)
class AppConfiguration