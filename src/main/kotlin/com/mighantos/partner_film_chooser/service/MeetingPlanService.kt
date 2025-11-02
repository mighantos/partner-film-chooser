package com.mighantos.partner_film_chooser.service

import com.mighantos.partner_film_chooser.dto.MeetingItemDto
import com.mighantos.partner_film_chooser.dto.MeetingPlanDto
import com.mighantos.partner_film_chooser.exception.BadRequestException
import com.mighantos.partner_film_chooser.exception.ForbiddenException
import com.mighantos.partner_film_chooser.model.MeetingInstance
import com.mighantos.partner_film_chooser.model.MeetingItem
import com.mighantos.partner_film_chooser.model.MeetingPlan
import com.mighantos.partner_film_chooser.repository.MeetingInstanceRepository
import com.mighantos.partner_film_chooser.repository.MeetingPlanRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime
import java.util.*
import kotlin.jvm.optionals.getOrElse

@Service
class MeetingPlanService(
    val repository: MeetingPlanRepository,
    val meetingInstanceRepository: MeetingInstanceRepository,
    val userService: UserService,
) {
    @Transactional(readOnly = true)
    fun findAll(): List<MeetingPlanDto> {
        return userService.current().meetingPlans.map(MeetingPlan::toDto)
    }

    @Transactional
    fun create(meetingPlanDto: MeetingPlanDto): MeetingPlan {
        val currentUser = userService.current()
        val partner = userService.find(meetingPlanDto.partner).getOrElse { throw BadRequestException() }
        if (currentUser == partner)
            throw BadRequestException()
        if (meetingPlanDto.startingDate.isBefore(OffsetDateTime.now()))
            throw BadRequestException()

        var meetingPlan = MeetingPlan(
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
        meetingPlan = repository.save(meetingPlan)
        meetingInstanceRepository.save(getNextInstanceOfMeetingPlan(meetingPlan))
        return meetingPlan
    }

    @Transactional
    fun addItem(uuid: UUID, meetingItemDto: MeetingItemDto) {
        val meetingPlan = repository.findById(uuid).getOrElse { throw BadRequestException() }
        validateAccess(meetingPlan)
        val meetingItem = meetingItemDto.toMeetingItem(meetingPlan)
        meetingPlan.meetingItems.add(meetingItem)
    }

    private fun getNextInstanceOfMeetingPlan(meetingPlan: MeetingPlan): MeetingInstance {
        val lastPlannedMeetingInstanceOpt = meetingInstanceRepository.findFirstByMeetingPlanOrderByDate(meetingPlan)
        val lastPlannedMeetingInstance = lastPlannedMeetingInstanceOpt.getOrElse {
            MeetingInstance(meetingPlan.startingDate, meetingPlan)
        }
        if (lastPlannedMeetingInstance.date.isBefore(OffsetDateTime.now())) {
            return MeetingInstance(lastPlannedMeetingInstance.date.plusDays(meetingPlan.period.toLong()), meetingPlan)
        }
        return lastPlannedMeetingInstance
    }

    private fun validateAccess(meetingPlan: MeetingPlan) {
        if (!meetingPlan.canManage(userService.current()))
            throw ForbiddenException()
    }
}