package com.mighantos.partner_film_chooser.dto

import com.mighantos.partner_film_chooser.util.MeetingItemType

data class MeetingItemDto(
    val title: String,
    val description: String,
    val itemType: MeetingItemType,
    val order: Short,
)