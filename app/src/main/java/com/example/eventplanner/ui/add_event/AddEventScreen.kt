package com.example.eventplanner.ui.add_event

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeler.sheets.date_time.DateTimeDialog
import com.maxkeppeler.sheets.date_time.models.DateTimeSelection
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventScreen(
    state: AddEventState,
    onShowDateDialog: () -> Unit,
    onDateChange: (LocalDateTime) -> Unit,
    onHideDialog: () -> Unit,
    onChangeTitle: (String) -> Unit,
    onChangePlace: (String) -> Unit,
    onChangeImageUrl: (String) -> Unit,
    onAddEventClick: () -> Unit,
    onNavigateToEventList: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 10.dp, horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.title,
            onValueChange = {
                onChangeTitle(it)
            },
            label = {
                Text(text = "Tytuł")
            },
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.place,
            onValueChange = {
                onChangePlace(it)
            },
            label = {
                Text(text = "Miejsce")
            },
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.imageUrl,
            onValueChange = {
                onChangeImageUrl(it)
            },
            label = {
                Text(text = "Zdjęcie")
            },
        )
        ChooseDateCard(date = state.dateDisplayString) {
            onShowDateDialog()
        }
        DateTimeDialog(
            state = UseCaseState(
                visible = state.showDateDialog,
            ),
            selection = DateTimeSelection.DateTime(
                onPositiveClick = { date ->
                    onDateChange(date)
                },
                onNegativeClick = {
                    onHideDialog()
                },
                selectedDate = state.dateDisplayString?.toLocalDate(),
                selectedTime = state.dateDisplayString?.toLocalTime(),
            ),
        )

        Button(
            onClick = { onAddEventClick() },
            enabled = !state.isLoading,
        ) {
            Text(text = "Dodaj wydarzenie")
        }

        if(state.isLoading) {
            CircularProgressIndicator()
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun AddEventScreenPreview() {
    Box(Modifier.fillMaxSize()) {
        AddEventScreen(
            state = AddEventState(
                title = "Afaesefef",
                place = "ullamcorper",
                dateDisplayString = null,
                imageUrl = "https://duckduckgo.com/?q=vivendo",
                latitude = 0.1,
                longitude = 2.3,
                showDateDialog = false,
            ),
            onDateChange = {},
            onShowDateDialog = {},
            onHideDialog = {},
            onChangeTitle = {},
            onChangePlace = {},
            onChangeImageUrl = {},
            onAddEventClick = {},
            onNavigateToEventList = {},
        )
    }
}