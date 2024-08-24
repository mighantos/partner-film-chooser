package com.mighantos.partner_film_chooser.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "keycloak")
data class KeycloakConfigurationProperties(
    val clientId: String,
)