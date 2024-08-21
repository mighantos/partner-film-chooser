package com.mighantos.partner_film_chooser.service

import com.mighantos.partner_film_chooser.exception.NotFoundException
import com.mighantos.partner_film_chooser.model.PartnersMeeting
import com.mighantos.partner_film_chooser.repository.PartnersMeetingRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class PartnersMeetingService(
    val repository: PartnersMeetingRepository,
) {
    @Transactional(readOnly = true)
    fun find(id: UUID): PartnersMeeting {
        return repository.findById(id).orElseThrow { NotFoundException() }
    }

    @Transactional
    fun create(partnersMeeting: PartnersMeeting): PartnersMeeting {
       return repository.save(partnersMeeting)
    }
}