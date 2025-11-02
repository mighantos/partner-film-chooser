package com.mighantos.partner_film_chooser.dto

import com.mighantos.partner_film_chooser.model.MeetingItem
import com.mighantos.partner_film_chooser.model.MeetingPlan
import com.mighantos.partner_film_chooser.util.MeetingItemType

data class MeetingItemDto(
    val title: String,
    val description: String,
    val itemType: MeetingItemType,
    val order: Short,
) {
    fun toMeetingItem(meetingPlan: MeetingPlan): MeetingItem {
        return MeetingItem(title, description, itemType, order, meetingPlan)
    }
}