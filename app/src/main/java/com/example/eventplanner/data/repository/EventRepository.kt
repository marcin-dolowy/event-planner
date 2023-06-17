package com.example.eventplanner.data.repository

import com.example.eventplanner.data.local.EventDao
import com.example.eventplanner.data.mapper.toDTO
import com.example.eventplanner.data.mapper.toDomain
import com.example.eventplanner.data.mapper.toEvent
import com.example.eventplanner.data.mapper.toEventList
import com.example.eventplanner.data.mapper.toEventModel
import com.example.eventplanner.data.network.EventService
import com.example.eventplanner.ui.models.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val eventDao: EventDao,
    private val eventService: EventService,
) {

    fun getEvents(): Flow<List<Event>> = flow {
        val events = eventDao.getEvents().firstOrNull()
        events?.map {
            it.toDomain()
        }?.let {
            emit(it)
        }

        val eventsFromNetwork = makeRequest(
            request = { eventService.getEvents() },
            mapper = { it?.toEventList() },
        )

        eventsFromNetwork?.let { newEvents ->
            eventDao.replaceEvents(
                newEvents = newEvents.map { it.toEventModel() },
            )
        }

        emitAll(
            eventDao.getEvents().map {
                it.map { it.toDomain() }
            }
        )
    }

    suspend fun deleteEvent(event: Event): Boolean {
        val response = makeRequest(
            request = { eventService.deleteEvent(event.id) },
            mapper = {},
        )

        return response?.let {
            eventDao.deleteEvent(event.toEventModel())
            true
        } ?: false
    }

    suspend fun saveEvent(event: Event): Event? {
        val response = makeRequest(
            request = {
                eventService.saveEvent(event.toDTO())
            },
            mapper = {
                it?.toEvent()
            },
        )

        response?.let {
            eventDao.addEvent(event.toEventModel())
        }

        return response
    }

    private suspend fun <T, Domain> makeRequest(
        request: suspend () -> Response<T>,
        mapper: (T?) -> Domain?,
    ): Domain? =
        try {
            val response = request()
            if (response.isSuccessful) {
                mapper(response.body())
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
}
