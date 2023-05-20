package com.example.eventplanner.ui.add_event

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventplanner.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ChooseDateCard(
    modifier: Modifier = Modifier,
    date: LocalDateTime?,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space = 10.dp),
        ) {
            val dateText = date?.let {
                val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
                date.format(formatter)
            }?:"Kliknij aby wybrać datę"

            Text(
                text = dateText,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
            )
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
                Icon(
                    modifier = Modifier.padding(vertical = 10.dp),
                    painter = painterResource(id = R.drawable.calendar_icon),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }

        }
    }
}

@Preview
@Composable
private fun ChooseDateCardPreview() {
    ChooseDateCard(
        date = null,
        onClick = {},
    )
}