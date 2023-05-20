package com.example.eventplanner.ui.events_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventplanner.ui.models.Event

@Composable
fun EventsListScreen(
    state: EventListState,
    modifier: Modifier = Modifier,
) {
    Surface(modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(state.events) { event ->
                EventCard(
                    event = event,
                    modifier = Modifier.padding(horizontal = 10.dp),
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun EventsListScreenPreview() {
    EventsListScreen(
        state = EventListState(
            events = buildList {
                val event = Event(
                    title = "Tytuł",
                    id = "",
                    place = "Kraków, Tauron Arena",
                    dateTextString = "20.05.2023 17:00",
                    imageUrl = "",
                    latitude = 15.0,
                    longitude = 15.0,
                )
                add(event)
                add(event)
                add(event)
                add(event)
                add(event)
                add(event)
            }
        )
    )
}
