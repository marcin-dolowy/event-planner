package com.example.eventplanner.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventModel(
    @PrimaryKey val id: String,
    val dateDisplayString: String,
    val imageUrl: String,
    val latitude: Double,
    val longitude: Double,
    val place: String,
    val title: String,
)
