package com.mighantos.partner_film_chooser.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class MeetingItemAnswer(
    @Column(nullable = false)
    var value: String,

    @ManyToOne
    @JoinColumn(name = "respondent_id", nullable = false)
    val respondent: User,

    @ManyToOne
    @JoinColumn(name = "meeting_instance_id", nullable = false)
    val meetingInstance: MeetingInstance,

    @ManyToOne
    @JoinColumn(name = "meeting_item_id", nullable = false)
    val meetingItem: MeetingItem,
) : BaseEntity()