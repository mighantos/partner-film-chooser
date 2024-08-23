package com.mighantos.partner_film_chooser.service

import com.mighantos.partner_film_chooser.dto.PartnersMeetingPlanDto
import com.mighantos.partner_film_chooser.exception.NotFoundException
import com.mighantos.partner_film_chooser.model.PartnersMeetingPlan
import com.mighantos.partner_film_chooser.repository.PartnersMeetingPlanRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class PartnersMeetingPlanService(
    val repository: PartnersMeetingPlanRepository,
    val userService: UserService,
) {
    @Transactional(readOnly = true)
    fun findAll(): Set<PartnersMeetingPlanDto> {
        return userService.current().partnersMeetings.map(PartnersMeetingPlan::toDto).toSet()
    }

    @Transactional(readOnly = true)
    fun find(id: UUID): PartnersMeetingPlan {
        return repository.findById(id).orElseThrow { NotFoundException() }
    }

    @Transactional
    fun create(partnersMeetingPlanDto: PartnersMeetingPlanDto): PartnersMeetingPlan {
        val partnersMeetingPlan = PartnersMeetingPlan(
            partnersMeetingPlanDto.title, userService.current(), partnersMeetingPlanDto.partner.toUser(),
            partnersMeetingPlanDto.startingDate, partnersMeetingPlanDto.period
        )
        return repository.save(partnersMeetingPlan)
    }
}