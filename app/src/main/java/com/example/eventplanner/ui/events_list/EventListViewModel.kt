package com.example.eventplanner.ui.events_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventplanner.domain.DeleteEventUseCase
import com.example.eventplanner.domain.GetEventsUseCase
import com.example.eventplanner.ui.models.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventListViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase,
    private val deleteEventUseCase: DeleteEventUseCase,
): ViewModel() {

    val state : StateFlow<EventListState>
        get() = mutableState

    private val mutableState = MutableStateFlow(
        EventListState(
            events = listOf()
        )
    )

    fun onDeleteEvent(event: Event) {
        viewModelScope.launch {
            deleteEventUseCase(event)
        }
    }

    init {
        viewModelScope.launch {
            getEventsUseCase()
                .collectLatest { events ->
                    mutableState.update {
                        it.copy(
                            events = events,
                        )
                    }
                }
        }
    }




}