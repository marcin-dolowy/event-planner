package com.example.eventplanner.ui.events_map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.eventplanner.ui.models.EventMarker
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState


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

            MyGoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                markers = state.markers,
                onMarkerClick = { marker ->
                    onChooseMarker(marker)
                },
            )
        }

    }

}





