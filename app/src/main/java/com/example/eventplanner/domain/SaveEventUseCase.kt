package com.example.eventplanner.domain

import com.example.eventplanner.data.mapper.toDTO
import com.example.eventplanner.data.mapper.toEvent
import com.example.eventplanner.data.mapper.toEventList
import com.example.eventplanner.data.network.EventService
import com.example.eventplanner.ui.models.Event
import javax.inject.Inject

class SaveEventUseCase @Inject constructor(
    private val eventService: EventService,
) {
    suspend operator fun invoke(event: Event): Event? {
        val response = eventService.saveEvent(event.toDTO())
        return if (response.isSuccessful) {
            response.body()?.toEvent()
        } else {
            null
        }
    }
}