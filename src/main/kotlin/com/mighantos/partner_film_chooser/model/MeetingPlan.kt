package com.mighantos.partner_film_chooser.model

import com.mighantos.partner_film_chooser.dto.MeetingPlanDto
import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
class MeetingPlan(
    @Column(nullable = false)
    val title: String,

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    val creator: User,

    @ManyToOne
    @JoinColumn(name = "partner_id", nullable = false)
    var partner: User,

    @Column(nullable = false)
    var startingDate: OffsetDateTime,

    @Column(nullable = false)
    var period: Short, //in days

    @OneToMany(mappedBy = "meetingPlan", cascade = [CascadeType.ALL])
    val meetingItems: MutableList<MeetingItem>,

    @OneToMany(mappedBy = "meetingPlan", cascade = [CascadeType.ALL])
    val meetingInstances: MutableList<MeetingInstance> = mutableListOf(),
) : BaseEntity() {
    fun canManage(user: User): Boolean {
        return creator == user || partner == user
    }

    fun toDto(): MeetingPlanDto {
        return MeetingPlanDto(
            id,
            title,
            creator.toDto(),
            partner.toDto(),
            startingDate,
            period,
            meetingItems.map(MeetingItem::toDto)
        )
    }
}