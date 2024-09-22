package com.mighantos.partner_film_chooser.controller

import com.mighantos.partner_film_chooser.dto.MeetingItemDto
import com.mighantos.partner_film_chooser.dto.MeetingPlanDto
import com.mighantos.partner_film_chooser.exception.BadRequestException
import com.mighantos.partner_film_chooser.service.MeetingPlanService
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/partners-meeting")
@PreAuthorize("hasRole('ROLE_USER')")
class MeetingPlanController(
    val service: MeetingPlanService,
) {
    @GetMapping
    fun getAllMeetings(): Set<MeetingPlanDto> {
        return service.findAll()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createMeeting(@RequestBody meetingPlanDto: MeetingPlanDto) {
        service.create(meetingPlanDto)
    }

    @PostMapping("/{id}/item")
    @ResponseStatus(HttpStatus.CREATED)
    fun addItemToMeeting(@PathVariable id: String, @RequestBody meetingItemDto: MeetingItemDto) {
        val uuid: UUID
        try {
            uuid = UUID.fromString(id)
        } catch (e: IllegalArgumentException) {
            throw BadRequestException()
        }
        service.addItem(uuid, meetingItemDto)
    }
}