package com.example.eventplanner.ui.events_list

import com.example.eventplanner.ui.models.Event

data class EventListState(
    val events: List<Event> = listOf(),
)
