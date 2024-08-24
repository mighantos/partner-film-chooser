package com.mighantos.partner_film_chooser.service

import com.mighantos.partner_film_chooser.dto.PartnersMeetingPlanDto
import com.mighantos.partner_film_chooser.exception.BadRequestException
import com.mighantos.partner_film_chooser.model.PartnersMeetingItem
import com.mighantos.partner_film_chooser.model.PartnersMeetingPlan
import com.mighantos.partner_film_chooser.repository.PartnersMeetingPlanRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrElse

@Service
class PartnersMeetingPlanService(
    val repository: PartnersMeetingPlanRepository,
    val userService: UserService,
) {
    @Transactional(readOnly = true)
    fun findAll(): Set<PartnersMeetingPlanDto> {
        return userService.current().partnersMeetingPlans.map(PartnersMeetingPlan::toDto).toSet()
    }

    @Transactional
    fun create(partnersMeetingPlanDto: PartnersMeetingPlanDto): PartnersMeetingPlan {
        val currentUser = userService.current()
        val partner = userService.find(partnersMeetingPlanDto.partner).getOrElse { throw BadRequestException() }
        if (currentUser == partner)
            throw BadRequestException()

        val partnersMeetingPlan = PartnersMeetingPlan(
            partnersMeetingPlanDto.title,
            currentUser,
            partner,
            partnersMeetingPlanDto.startingDate,
            partnersMeetingPlanDto.period,
            mutableListOf()
        )
        val partnersMeetingItems = partnersMeetingPlanDto.meetingItems.map {
            PartnersMeetingItem(it.title, it.description, it.itemType, it.order, partnersMeetingPlan)
        }
        partnersMeetingPlan.meetingItems.addAll(partnersMeetingItems)
        return repository.save(partnersMeetingPlan)
    }
}