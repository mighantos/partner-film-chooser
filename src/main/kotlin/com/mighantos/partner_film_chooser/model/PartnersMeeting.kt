package com.mighantos.partner_film_chooser.model

import jakarta.persistence.*
import java.util.*

@Entity
class PartnersMeeting(
    @Column(nullable = false)
    var meetingDate: Date,
): BaseEntity()