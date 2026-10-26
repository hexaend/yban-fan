package com.ybanfan.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.ybanfan.app.R
import com.ybanfan.app.data.Portrait
import com.ybanfan.app.ui.theme.CarpetRed

@Composable
fun HomeScreen(portrait: Portrait, onOpenTopVideos: () -> Unit) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Image(
                painter = painterResource(R.drawable.yban),
                contentDescription = portrait.name,
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .border(3.dp, CarpetRed, CircleShape),
            )
            Column {
                Text(portrait.name, style = MaterialTheme.typography.headlineLarge)
                Text("@${portrait.handle}", color = MaterialTheme.colorScheme.secondary)
            }
        }

        Text(
            text = portrait.tagline,
            style = MaterialTheme.typography.titleMedium,
            fontStyle = FontStyle.Italic,
        )

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            portrait.stats.forEach { stat ->
                Column(Modifier.weight(1f)) {
                    Text(stat.value, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                    Text(stat.label, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }

        SectionTitle("Кто это")
        Text(portrait.about, style = MaterialTheme.typography.bodyLarge)

        SectionTitle("Факты")
        DashList(portrait.facts)

        SectionTitle("Мемы и темы")
        DashList(portrait.memes)

        SectionTitle("Что говорят Ыбанисты")
        DashList(portrait.fansSay)

        SectionTitle("Что говорят остальные")
        DashList(portrait.othersSay)

        Button(
            onClick = onOpenTopVideos,
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Топ смешных видео")
        }
    }
}
