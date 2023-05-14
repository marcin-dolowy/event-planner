package com.example.eventplanner.ui.add_event

data class AddEventState(
    val title: String = "",
    val place: String = "",
    val dateDisplayString: String = "",
    val imageUrl: String = "",
    val latitude: Double,
    val longitude: Double,
)
