package com.mighantos.partner_film_chooser.model

import jakarta.persistence.*
import java.util.*

@Entity(name = "user_app")
class User(
    @Id
    val id: UUID
) {
    @OneToMany(mappedBy = "creator")
    private val partnersMeetingsCreators: Set<PartnersMeeting> = setOf()

    @OneToMany(mappedBy = "partner")
    private val partnersMeetingsPartners: Set<PartnersMeeting> = setOf()

    @Transient
    val partnersMeetings: MutableSet<PartnersMeeting> = mutableSetOf()

    @PostLoad
    fun postLoad() {
        partnersMeetings.addAll(partnersMeetingsCreators)
        partnersMeetings.addAll(partnersMeetingsPartners)
    }
}