package com.example.eventplanner.data.mapper

import com.example.eventplanner.data.models.EventDTO
import com.example.eventplanner.ui.models.Event

fun EventDTO?.toEventList(): List<Event> {
    return this?.let {
        it.map { item ->
            with(item) {
                Event(
                    id = id,
                    title = title,
                    place = place,
                    dateDisplayString = dateDisplayString,
                    imageUrl = imageUrl,
                    latitude = latitude,
                    longitude = longitude,
                )
            }

        }
    }?: listOf()
}