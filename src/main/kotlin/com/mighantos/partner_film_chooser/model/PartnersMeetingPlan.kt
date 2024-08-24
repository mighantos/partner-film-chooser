package com.mighantos.partner_film_chooser.model

import com.mighantos.partner_film_chooser.dto.PartnersMeetingPlanDto
import jakarta.persistence.*
import java.util.*

@Entity
class PartnersMeetingPlan(
    @Column(nullable = false)
    val title: String,

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    val creator: User,

    @ManyToOne
    @JoinColumn(name = "partner_id", nullable = false)
    var partner: User,

    @Column(nullable = false)
    var startingDate: Date,

    @Column(nullable = false)
    var period: Short,

    @OneToMany(mappedBy = "meetingPlan", cascade = [CascadeType.ALL])
    val meetingItems: MutableList<PartnersMeetingItem>,
) : BaseEntity() {
    fun toDto(): PartnersMeetingPlanDto {
        return PartnersMeetingPlanDto(
            id,
            title,
            creator.toDto(),
            partner.toDto(),
            startingDate,
            period,
            meetingItems.map(PartnersMeetingItem::toDto)
        )
    }
}