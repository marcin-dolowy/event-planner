package com.example.eventplanner.ui.add_event

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(): ViewModel() {
    val state : StateFlow<AddEventState>
        get() = mutableState

    private val mutableState = MutableStateFlow(
        AddEventState()
    )

    fun onDateChange(date: LocalDateTime) {
        updateState {
            copy(
                dateDisplayString = date,
                showDateDialog = false,
            )
        }
    }

    fun onShowDialog() {
        updateState {
            copy(showDateDialog = true)
        }
    }

    fun onHideDialog() {
        updateState {
            copy(showDateDialog = false)
        }
    }

    fun onChangeTitle(newTitle: String) {
        updateState {
            copy(title = newTitle)
        }
    }

    fun onChangePlace(newPlace: String) {
        updateState {
            copy(title = newPlace)
        }
    }

    fun onChangeImageUrl(newImageUrl: String) {
        updateState {
            copy(title = newImageUrl)
        }
    }

    

    private fun updateState(update: AddEventState.() -> AddEventState) {
        mutableState.update {
            update(it)
        }
    }
}