package com.mighantos.partner_film_chooser.model

import jakarta.persistence.*
import java.util.*

@Entity
class MeetingInstance(
    @Column(nullable = false)
    var date: Date,

    @ManyToOne
    @JoinColumn(name = "meeting_plan_id", nullable = false)
    val meetingPlan: MeetingPlan,

    @OneToMany(mappedBy = "meetingItem", cascade = [CascadeType.ALL])
    val itemAnswers: MutableList<MeetingItemAnswer>,
) : BaseEntity()