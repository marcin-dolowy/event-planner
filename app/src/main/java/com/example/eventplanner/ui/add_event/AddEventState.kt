package com.example.eventplanner.ui.add_event

import java.time.LocalDateTime

data class AddEventState(
    val title: String = "",
    val place: String = "",
    val dateDisplayString: LocalDateTime? = null,
    val imageUrl: String = "",
    val latitude: Double? = null,
    val longitude: Double? = null,
    val showDateDialog: Boolean = false,
)
