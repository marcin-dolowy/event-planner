package com.example.eventplanner.ui.add_event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventplanner.domain.SaveEventUseCase
import com.example.eventplanner.ui.models.Event
import com.example.eventplanner.ui.utils.ToastViewer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(
    private val saveEventUseCase: SaveEventUseCase,
    private val toastViewer: ToastViewer,
): ViewModel() {
    val state : StateFlow<AddEventState>
        get() = mutableState

    private val mutableState = MutableStateFlow(
        AddEventState(
            latitude = 0.0, // TODO: Change
            longitude = 0.0, // TODO: Change
        )
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
            copy(place = newPlace)
        }
    }

    fun onChangeImageUrl(newImageUrl: String) {
        updateState {
            copy(imageUrl = newImageUrl)
        }
    }

    

    private fun updateState(update: AddEventState.() -> AddEventState) {
        mutableState.update {
            update(it)
        }
    }

    fun onAddEventClick() {
        updateState {
            copy(isLoading = true)
        }
        viewModelScope.launch {
            val currentState = state.value
            val validatedEvent = currentState.toValidatedEvent()

            if(validatedEvent != null) {
                val result = saveEventUseCase(validatedEvent)
                if(result == null) {
                    showToast("Network error")
                }
                else {
                    showToast("Success!!!!!!")
                }
            }
            else {
                showToast("Some fields are empty!")
            }

            updateState {
                copy(isLoading = false)
            }
        }
    }

    private fun showToast(message: String) {
        toastViewer.showToast(message)
    }

    private fun AddEventState.toValidatedEvent(): Event? =
        try {
            Event(
                title = title.assertNotEmpty(),
                place = place.assertNotEmpty(),
                dateDisplayString = dateDisplayString!!.formatToDatetimeFormat(),
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
}