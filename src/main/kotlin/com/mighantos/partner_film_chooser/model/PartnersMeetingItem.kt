package com.mighantos.partner_film_chooser.model

import com.mighantos.partner_film_chooser.dto.PartnersMeetingItemDto
import com.mighantos.partner_film_chooser.util.MeetingItemType
import jakarta.persistence.*

@Entity
class PartnersMeetingItem(
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
    val meetingPlan: PartnersMeetingPlan,
) : BaseEntity() {
    fun toDto(): PartnersMeetingItemDto {
        return PartnersMeetingItemDto(title, description, itemType, itemOrder)
    }
}