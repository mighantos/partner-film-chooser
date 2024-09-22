package com.mighantos.partner_film_chooser.model

import com.mighantos.partner_film_chooser.dto.MeetingItemDto
import com.mighantos.partner_film_chooser.util.MeetingItemType
import jakarta.persistence.*

@Entity
class MeetingItem(
    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val description: String,

    @Enumerated(EnumType.STRING)
    val itemType: MeetingItemType,

    @Column(nullable = false)
    val itemOrder: Short,

    @ManyToOne
    @JoinColumn(name = "meeting_plan_id", nullable = false)
    val meetingPlan: MeetingPlan,
) : BaseEntity() {
    fun toDto(): MeetingItemDto {
        return MeetingItemDto(title, description, itemType, itemOrder)
    }
}