package com.example.eventplanner.ui.events_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventplanner.domain.GetEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventListViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase,
): ViewModel() {

    val state : StateFlow<EventListState>
        get() = mutableState

    private val mutableState = MutableStateFlow(
        EventListState(
            events = listOf()
        )
    )

    init {
        viewModelScope.launch {
            val events = getEventsUseCase()
            events?.let {
                mutableState.update {
                    it.copy(
                        events = events,
                    )
                }
            }
        }
    }




}