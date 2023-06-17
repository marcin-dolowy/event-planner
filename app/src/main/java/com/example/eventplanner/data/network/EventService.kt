package com.example.eventplanner.data.network

import com.example.eventplanner.data.models.EventDTO
import com.example.eventplanner.data.models.EventDTOItem
import com.example.eventplanner.data.models.SaveEventDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventService {

    @GET("/events")
    suspend fun getEvents(): Response<EventDTO>

    @DELETE("/events/{id}")
    suspend fun deleteEvent(@Path("id") eventId: String): Response<Void>

    @POST("/events")
    suspend fun saveEvent(@Body event: SaveEventDTO): Response<EventDTOItem>
}
