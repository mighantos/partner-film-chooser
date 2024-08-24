package com.mighantos.partner_film_chooser.controller

import com.mighantos.partner_film_chooser.dto.PartnersMeetingPlanDto
import com.mighantos.partner_film_chooser.service.PartnersMeetingPlanService
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/partners-meeting")
@PreAuthorize("hasRole('ROLE_USER')")
class PartnersMeetingPlanController(
    val service: PartnersMeetingPlanService,
) {
    @GetMapping
    fun getAllMeetings(): Set<PartnersMeetingPlanDto> {
        return service.findAll()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createMeeting(@RequestBody partnersMeetingPlanDto: PartnersMeetingPlanDto) {
        service.create(partnersMeetingPlanDto)
    }
}