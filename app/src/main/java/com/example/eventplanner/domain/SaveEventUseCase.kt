package com.example.eventplanner.domain

import com.example.eventplanner.data.repository.EventRepository
import com.example.eventplanner.ui.models.Event
import javax.inject.Inject

class SaveEventUseCase @Inject constructor(
    private val eventRepository: EventRepository,
) {
    suspend operator fun invoke(event: Event): Event? =
        eventRepository.saveEvent(event)
}