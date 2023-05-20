package com.example.eventplanner.ui.add_event

import com.example.eventplanner.ui.models.Event


fun AddEventState.toValidatedEvent(): Event? =
    try {
        Event(
            title = title.assertNotEmpty(),
            place = place.assertNotEmpty(),
            dateTextString = dateDisplayString!!.formatToDatetimeFormat(),
            imageUrl = imageUrl.assertNotEmpty(),
            latitude = latitude!!,
            longitude = longitude!!,
        )
    } catch (e: Exception) {
        null
    }

private fun String.assertNotEmpty(): String =
    ifEmpty {
        throw IllegalArgumentException()
    }