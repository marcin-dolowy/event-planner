package com.example.eventplanner.data.models

import androidx.annotation.Keep

@Keep
data class EventDTOItem(
    val dateDisplayString: String,
    val id: String,
    val imageUrl: String,
    val latitude: Double,
    val longitude: Double,
    val place: String,
    val title: String
)