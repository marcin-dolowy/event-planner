package com.example.eventplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eventplanner.ui.add_event.AddEventScreen
import com.example.eventplanner.ui.add_event.AddEventViewModel
import com.example.eventplanner.ui.events_list.EventListViewModel
import com.example.eventplanner.ui.events_list.EventsListScreen
import com.example.eventplanner.ui.theme.EventPlannerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventPlannerTheme {
                /*val eventListViewModel = hiltViewModel<EventListViewModel>()
                val eventListState = eventListViewModel.state.collectAsState()
                EventsListScreen(
                    state = eventListState.value
                )*/
                val addEventViewModel = hiltViewModel<AddEventViewModel>()
                val addEventState = addEventViewModel.state.collectAsState()
                AddEventScreen(
                    state = addEventState.value,
                    onShowDateDialog = { addEventViewModel.onShowDialog() },
                    onDateChange = { addEventViewModel.onDateChange(it) },
                    onHideDialog = { addEventViewModel.onHideDialog() }
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EventPlannerTheme {
        Greeting("Android")
    }
}