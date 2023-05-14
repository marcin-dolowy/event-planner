package com.example.eventplanner.data.network

import com.example.eventplanner.data.models.EventDTO
import retrofit2.Response
import retrofit2.http.GET

interface EventService {

    @GET("/events")
    suspend fun getEvents(): Response<EventDTO>
}
