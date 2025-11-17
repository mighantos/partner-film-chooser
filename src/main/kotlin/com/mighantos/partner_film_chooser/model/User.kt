package com.mighantos.partner_film_chooser.model

import com.mighantos.partner_film_chooser.dto.UserDto
import jakarta.persistence.*
import java.util.*

@Entity(name = "app_user")
class User(
    @Id
    val id: UUID,
    @Column(nullable = false)
    val userName: String,
    @Column(nullable = false)
    val firstName: String,
    @Column(nullable = false)
    val lastName: String,
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