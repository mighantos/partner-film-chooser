package com.mighantos.partner_film_chooser.model

import com.mighantos.partner_film_chooser.dto.UserDto
import jakarta.persistence.*
import java.util.*

@Entity(name = "app_user")
class User(
    @Id
    val id: UUID,
) {
    @OneToMany(mappedBy = "creator")
    private val meetingPlansAsCreator: Set<MeetingPlan> = setOf()

    @OneToMany(mappedBy = "partner")
    private val meetingPlansAsPartner: Set<MeetingPlan> = setOf()

    @Transient
    var meetingPlans: MutableSet<MeetingPlan> = mutableSetOf()

    @PostLoad
    fun postLoad() {
        meetingPlans = mutableSetOf()
        meetingPlans.addAll(meetingPlansAsCreator)
        meetingPlans.addAll(meetingPlansAsPartner)
    }

    fun toDto(): UserDto {
        return UserDto(id)
    }
}