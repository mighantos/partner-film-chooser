package com.mighantos.partner_film_chooser.model

import com.mighantos.partner_film_chooser.dto.UserDto
import jakarta.persistence.*
import java.util.*

@Entity(name = "user_app")
class User(
    @Id
    val id: UUID
) {
    @OneToMany(mappedBy = "creator")
    private val partnersMeetingsCreators: Set<PartnersMeetingPlan> = setOf()

    @OneToMany(mappedBy = "partner")
    private val partnersMeetingsPartners: Set<PartnersMeetingPlan> = setOf()

    @Transient
    val partnersMeetings: MutableSet<PartnersMeetingPlan> = mutableSetOf()

    @PostLoad
    fun postLoad() {
        partnersMeetings.addAll(partnersMeetingsCreators)
        partnersMeetings.addAll(partnersMeetingsPartners)
    }

    fun toDto(): UserDto {
        return UserDto(id)
    }
}