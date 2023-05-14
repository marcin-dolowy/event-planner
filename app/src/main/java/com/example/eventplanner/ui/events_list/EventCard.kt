package com.example.eventplanner.ui.events_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.eventplanner.R
import com.example.eventplanner.ui.models.Event
import com.example.eventplanner.ui.utils.myLoadingEffect

@Composable
fun EventCard(
    modifier: Modifier = Modifier,
    event: Event,
    isLoading: Boolean = false,
    onCardClick: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onCardClick()
            },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
    ) {
        Column(
            modifier = Modifier.padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val screenWidth = LocalConfiguration.current.screenWidthDp
            AsyncImage(
                modifier = Modifier
                    .padding(bottom = 7.dp)
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth()
                    .height((screenWidth*0.66).dp)
                    .myLoadingEffect(isLoading),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(event.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                error = painterResource(id = R.drawable.image_placeholder),
                placeholder = painterResource(id = R.drawable.image_placeholder),
                contentScale = ContentScale.FillBounds
            )

            Text(
                text = event.tittle,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .fillMaxWidth()
                    .myLoadingEffect(isLoading),
                fontWeight = FontWeight.SemiBold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .padding(vertical = 5.dp)
                    .fillMaxWidth()
                    .myLoadingEffect(isLoading),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = event.place,
                    fontWeight = FontWeight.Light,
                    fontSize = 23.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = event.dateDisplayString,
                    fontWeight = FontWeight.Normal,
                    fontSize = 23.sp,
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EventCardPreview() {
    EventCard(
        event = Event(
            tittle = "Tytuł",
            eventId = "",
            place = "Kraków, Tauron Arena",
            dateDisplayString = "20.05.2023 17:00",
            imageUrl = "",
            latitude = 15.0,
            longitude = 15.0,
        ),
    )
}