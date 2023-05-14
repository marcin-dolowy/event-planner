package com.example.eventplanner.data.network

import com.example.eventplanner.data.models.EventDTO
import com.example.eventplanner.data.models.EventDTOItem
import com.example.eventplanner.data.models.SaveEventDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EventService {

    @GET("/events")
    suspend fun getEvents(): Response<EventDTO>

    @POST("/events")
    suspend fun saveEvent(@Body event: SaveEventDTO): Response<EventDTOItem>
}
