package com.mighantos.partner_film_chooser.model

import com.mighantos.partner_film_chooser.dto.UserDto
import jakarta.persistence.*
import java.util.*

@Entity(name = "app_user")
class User(
    @Id
    val id: UUID,
) {
    @OneToMany(mappedBy = "creator")
    private val partnersMeetingPlansAsCreator: Set<PartnersMeetingPlan> = setOf()

    @OneToMany(mappedBy = "partner")
    private val partnersMeetingPlansAsPartner: Set<PartnersMeetingPlan> = setOf()

    @Transient
    var partnersMeetingPlans: MutableSet<PartnersMeetingPlan> = mutableSetOf()

    @PostLoad
    fun postLoad() {
        partnersMeetingPlans = mutableSetOf()
        partnersMeetingPlans.addAll(partnersMeetingPlansAsCreator)
        partnersMeetingPlans.addAll(partnersMeetingPlansAsPartner)
    }

    fun toDto(): UserDto {
        return UserDto(id)
    }
}