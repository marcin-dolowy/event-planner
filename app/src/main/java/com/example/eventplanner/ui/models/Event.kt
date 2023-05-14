package com.example.eventplanner.ui.models

data class Event(
    val eventId: String = "",
    val tittle: String = "",
    val place: String = "",
    val dateDisplayString: String = "",
    val imageUrl: String = "",
    val latitude: Double,
    val longitude: Double,
)
