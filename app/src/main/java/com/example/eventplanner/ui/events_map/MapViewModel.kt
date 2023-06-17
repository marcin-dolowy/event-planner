package com.example.eventplanner.ui.events_map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventplanner.R
import com.example.eventplanner.domain.GetEventsUseCase
import com.example.eventplanner.ui.models.EventMarker
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    getEventsUseCase: GetEventsUseCase,
): ViewModel() {
    val state : StateFlow<MapState>
        get() = mutableState

    private val mutableState = MutableStateFlow(MapState())

    init {
        viewModelScope.launch {
            val eventsFlow = getEventsUseCase()
            eventsFlow.collectLatest { events ->
                mutableState.update { mapState ->
                    mapState.copy(
                        markers = events.map {
                            with(it) {
                                EventMarker(
                                    position = LatLng(
                                        latitude,
                                        longitude,
                                    ),
                                    iconId = R.drawable.marker_icon,
                                    eventTittle = title,
                                    eventId = id,
                                    place = place,
                                    dateTextString = dateTextString,
                                )
                            }
                        }
                    )
                }
            }
        }
    }

    fun onChooseEvent(newChosenMarker: EventMarker) {
        mutableState.update {
            val previousSelected = it.chosenEvent

            it.copy(
                markers = it.markers.map { marker ->
                    marker.copy(
                        iconId = if(newChosenMarker != previousSelected && newChosenMarker.eventId == marker.eventId)
                            R.drawable.selected_marker_icon
                        else
                            R.drawable.marker_icon
                    )

                },
                chosenEvent = if(newChosenMarker != previousSelected)
                    newChosenMarker
                else null,
            )
        }
    }
}