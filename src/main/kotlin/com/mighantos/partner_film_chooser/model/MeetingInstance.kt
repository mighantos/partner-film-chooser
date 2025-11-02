package com.mighantos.partner_film_chooser.model

import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
class MeetingInstance(
    @Column(nullable = false)
    var date: OffsetDateTime,

    @ManyToOne
    @JoinColumn(name = "meeting_plan_id", nullable = false)
    val meetingPlan: MeetingPlan,

    @OneToMany(mappedBy = "meetingInstance", cascade = [CascadeType.ALL])
    val itemAnswers: MutableList<MeetingItemAnswer> = mutableListOf(),
) : BaseEntity()