package com.mighantos.partner_film_chooser.repository

import com.mighantos.partner_film_chooser.model.MeetingPlan
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MeetingPlanRepository : JpaRepository<MeetingPlan, UUID>