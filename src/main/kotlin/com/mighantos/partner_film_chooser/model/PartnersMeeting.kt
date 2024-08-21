package com.mighantos.partner_film_chooser.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.util.*

@Entity
class PartnersMeeting(
    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    val creator: User,

    @ManyToOne
    @JoinColumn(name = "partner_id", nullable = false)
    var partner: User,

    @Column(nullable = false)
    var meetingDate: Date,
) : BaseEntity()