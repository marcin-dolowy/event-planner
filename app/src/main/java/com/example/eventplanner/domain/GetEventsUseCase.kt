package com.example.eventplanner.domain

import com.example.eventplanner.data.repository.EventRepository
import com.example.eventplanner.ui.models.Event
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
    private val eventRepository: EventRepository,
) {
    operator fun invoke(): Flow<List<Event>> =
        eventRepository.getEvents()
}
