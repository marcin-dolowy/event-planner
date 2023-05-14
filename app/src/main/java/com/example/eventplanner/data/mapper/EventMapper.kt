package com.example.eventplanner.data.mapper

import com.example.eventplanner.data.models.EventDTO
import com.example.eventplanner.data.models.EventDTOItem
import com.example.eventplanner.data.models.SaveEventDTO
import com.example.eventplanner.ui.models.Event

fun EventDTO?.toEventList(): List<Event> {
    return this?.let {
        it.map { item ->
            item.toEvent()
        }
    }?: listOf()
}

fun EventDTOItem.toEvent(): Event {
    return Event(
        id = id,
        title = title,
        place = place,
        dateDisplayString = dateDisplayString,
        imageUrl = imageUrl,
        latitude = latitude,
        longitude = longitude,
    )
}

fun Event.toDTO(): SaveEventDTO {
    return SaveEventDTO(
        title = title,
        place = place,
        dateDisplayString = dateDisplayString,
        imageUrl = imageUrl,
        latitude = latitude,
        longitude = longitude,
    )
}