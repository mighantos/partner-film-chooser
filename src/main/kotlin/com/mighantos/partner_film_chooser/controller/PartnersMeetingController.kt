package com.mighantos.partner_film_chooser.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/partners-meeting")
@PreAuthorize("hasRole('ROLE_USER')")
class PartnersMeetingController {

    @GetMapping
    fun getAllMeetings(): String {
        return "Hello world"
    }
}