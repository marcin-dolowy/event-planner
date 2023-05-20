package com.example.eventplanner.ui.events_map

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventplanner.ui.models.EventMarker
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch


@Composable
fun MapScreen(
    state: MapState,
    onChooseMarker: (EventMarker) -> Unit,
    modifier: Modifier = Modifier,
) {
    val cameraPositionState = rememberCameraPositionState()
    {
        position = CameraPosition.fromLatLngZoom(LatLng(50.0624, 19.9116), 12f)
    }

    Column(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize())
        {
            val animationScope = rememberCoroutineScope()
            MyGoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                markers = state.markers,
                onMarkerClick = { marker ->
                    onChooseMarker(marker)
                    animationScope.launch {
                        cameraPositionState.animate(CameraUpdateFactory.newLatLng(marker.position))
                    }
                },
            )

            EventPreviewCard(
                modifier = Modifier
                    .padding(all = 15.dp)
                    .align(Alignment.BottomCenter),
                event = state.chosenEvent,
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun EventPreviewCard(
    modifier: Modifier,
    event: EventMarker?
) {
    AnimatedContent(
        modifier = modifier,
        targetState = event?.eventId,
        transitionSpec = {
            fadeIn() + slideInVertically(animationSpec = tween(400),
                initialOffsetY = { fullHeight -> fullHeight }) with
                    fadeOut(animationSpec = tween(200))
        }
    ) {
        event?.let {
            Card {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(space = 5.dp)
                ) {
                    Text(text = event.eventTittle, fontSize = 16.sp, color = Color.Green)
                    Text(text = event.place)
                    Text(text = event.dateTextString)
                }
            }
        }
    }
}





