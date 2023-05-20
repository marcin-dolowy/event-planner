package com.example.eventplanner.ui.events_map

import com.example.eventplanner.ui.models.EventMarker

data class MapState(
    val markers: List<EventMarker> = listOf(),
    val isLoading: Boolean = true,
    val chosenEvent: EventMarker? = null,
)
