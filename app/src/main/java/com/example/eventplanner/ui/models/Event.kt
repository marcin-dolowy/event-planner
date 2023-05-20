package com.example.eventplanner.ui.models

data class Event(
    val id: String = "",
    val title: String = "",
    val place: String = "",
    val dateTextString: String = "",
    val imageUrl: String = "",
    val latitude: Double,
    val longitude: Double,
)
