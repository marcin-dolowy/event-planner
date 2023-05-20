package com.example.eventplanner.ui.add_event

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class AddEventState(
    val title: String = "",
    val place: String = "",
    val dateDisplayString: LocalDateTime? = null,
    val imageUrl: String = "",
    val latitude: Double? = null,
    val longitude: Double? = null,
    val showDateDialog: Boolean = false,
    val isLoading: Boolean = false,
)

fun LocalDateTime.formatToDatetimeFormat(): String {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
    return format(formatter)
}