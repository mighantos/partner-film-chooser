package com.mighantos.partner_film_chooser.dto

import java.util.*

data class MeetingPlanDto(
    val id: UUID?,
    val title: String,
    val creator: UserDto?,
    val partner: UserDto,
    val startingDate: Date,
    val period: Short,
    val meetingItems: List<MeetingItemDto>,
)