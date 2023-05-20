package com.example.eventplanner.domain

import com.example.eventplanner.data.mapper.toEventList
import com.example.eventplanner.data.network.EventService
import com.example.eventplanner.ui.models.Event
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
    private val eventService: EventService,
) {
    suspend operator fun invoke(): List<Event>? =
        try {
            val response = eventService.getEvents()
            if(response.isSuccessful) {
                response.body().toEventList()
            }
            else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
}
