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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eventplanner.ui.add_event.AddEventEffect
import com.example.eventplanner.ui.add_event.AddEventScreen
import com.example.eventplanner.ui.add_event.AddEventViewModel
import com.example.eventplanner.ui.events_list.EventListViewModel
import com.example.eventplanner.ui.events_list.EventsListScreen
import com.example.eventplanner.ui.theme.EventPlannerTheme
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.Route

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventPlannerTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.AddEvent.routeName) {
                    composable(route = Routes.EventList.routeName) {
                        EventListDestination()
                    }
                    composable(route = Routes.AddEvent.routeName) {
                        addEventDestination(onNavigateToEventList = {
                            navController.navigate(Routes.EventList.routeName)
                        })
                    }
                }


            }
        }
    }
}

sealed class Routes(val routeName: String) {
    object EventList: Routes(routeName = "EventList")
    object AddEvent: Routes(routeName = "AddEvent")
}

@Composable
private fun EventListDestination() {
    val eventListViewModel = hiltViewModel<EventListViewModel>()
    val eventListState = eventListViewModel.state.collectAsState()
    EventsListScreen(
        state = eventListState.value
    )
}

@Composable
private fun HandleAddEventEffect(
    addEventViewModel: AddEventViewModel,
    action: (AddEventEffect) -> Unit
) {
    val currentEffect = addEventViewModel.effect.collectAsState().value
    if(currentEffect != null) {
        action(currentEffect)
        addEventViewModel.clearEffect()
    }
}

@Composable
private fun addEventDestination(
    onNavigateToEventList: () -> Unit,
) {
    val addEventViewModel = hiltViewModel<AddEventViewModel>()
    val addEventState = addEventViewModel.state.collectAsState()

    AddEventScreen(
        state = addEventState.value,
        onShowDateDialog = { addEventViewModel.onShowDialog() },
        onDateChange = { addEventViewModel.onDateChange(it) },
        onHideDialog = { addEventViewModel.onHideDialog() },
        onChangeTitle = { addEventViewModel.onChangeTitle(it) },
        onChangePlace = { addEventViewModel.onChangePlace(it) },
        onChangeImageUrl = { addEventViewModel.onChangeImageUrl(it) },
        onAddEventClick = { addEventViewModel.onAddEventClick() },
        onNavigateToEventList = { onNavigateToEventList() },
    )

    HandleAddEventEffect(addEventViewModel = addEventViewModel) { effect ->
        when(effect) {
            is AddEventEffect.NavigateToEventList -> {
                onNavigateToEventList()
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