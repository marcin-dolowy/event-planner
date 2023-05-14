package com.example.eventplanner.ui.add_event

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventScreen(
    state: AddEventState,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        OutlinedTextField(
            value = state.title,
            onValueChange = {

            },
            label = {
                Text(text = "Tytuł")
            },
        )
        OutlinedTextField(
            value = state.place,
            onValueChange = {

            },
            label = {
                Text(text = "Miejsce")
            },
        )
        OutlinedTextField(
            value = state.imageUrl,
            onValueChange = {

            },
            label = {
                Text(text = "Zdjęcie")
            },
        )


    }

}


@Preview(showBackground = true)
@Composable
private fun AddEventScreenPreview() {
    AddEventScreen(
        state = AddEventState(
            title = "Afaesefef",
            place = "ullamcorper",
            dateDisplayString = "suavitate",
            imageUrl = "https://duckduckgo.com/?q=vivendo",
            latitude = 0.1,
            longitude = 2.3
        )
    )
}