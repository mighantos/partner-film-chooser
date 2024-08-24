package com.mighantos.partner_film_chooser.security

import com.mighantos.partner_film_chooser.config.properties.KeycloakConfigurationProperties
import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt

class KeycloakGrantedAuthoritiesExtractor(
    private val keycloakConfigurationProperties: KeycloakConfigurationProperties,
) : Converter<Jwt, Collection<GrantedAuthority>> {
    private val resourceAccessClaim: String = "resource_access"
    private val roleClaim: String = "roles"

    override fun convert(jwt: Jwt): Collection<GrantedAuthority> {
        val authorities = mutableSetOf<GrantedAuthority>()
        val accessClaim = jwt.claims[resourceAccessClaim] as Map<*, *>
        val appAccessClaim = accessClaim[keycloakConfigurationProperties.clientId] as Map<*, *>
        val roles = appAccessClaim[roleClaim] as Collection<String>
        authorities.addAll(generateAuthoritiesFromClaim(roles))
        return authorities
    }

    private fun generateAuthoritiesFromClaim(roles: Collection<String>): Collection<GrantedAuthority> {
        return roles.map { role -> SimpleGrantedAuthority("ROLE_$role") }.toList()
    }
}