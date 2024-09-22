package com.mighantos.partner_film_chooser.service

import com.mighantos.partner_film_chooser.dto.MeetingItemDto
import com.mighantos.partner_film_chooser.dto.MeetingPlanDto
import com.mighantos.partner_film_chooser.exception.BadRequestException
import com.mighantos.partner_film_chooser.model.MeetingItem
import com.mighantos.partner_film_chooser.model.MeetingPlan
import com.mighantos.partner_film_chooser.repository.MeetingPlanRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.jvm.optionals.getOrElse

@Service
class MeetingPlanService(
    val repository: MeetingPlanRepository,
    val userService: UserService,
) {
    @Transactional(readOnly = true)
    fun findAll(): Set<MeetingPlanDto> {
        return userService.current().meetingPlans.map(MeetingPlan::toDto).toSet()
    }

    @Transactional
    fun create(meetingPlanDto: MeetingPlanDto): MeetingPlan {
        val currentUser = userService.current()
        val partner = userService.find(meetingPlanDto.partner).getOrElse { throw BadRequestException() }
        if (currentUser == partner)
            throw BadRequestException()

        val meetingPlan = MeetingPlan(
            meetingPlanDto.title,
            currentUser,
            partner,
            meetingPlanDto.startingDate,
            meetingPlanDto.period,
            mutableListOf()
        )
        val meetingItems = meetingPlanDto.meetingItems.map {
            MeetingItem(it.title, it.description, it.itemType, it.order, meetingPlan)
        }
        meetingPlan.meetingItems.addAll(meetingItems)
        return repository.save(meetingPlan)
    }

    fun addItem(uuid: UUID, meetingItemDto: MeetingItemDto) {
        TODO("Not yet implemented")
    }
}