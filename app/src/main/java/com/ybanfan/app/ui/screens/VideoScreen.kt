package com.ybanfan.app.ui.screens

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.ybanfan.app.data.Video

@Composable
fun VideoScreen(video: Video, onBack: () -> Unit) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        TextButton(onClick = onBack) { Text("← Назад") }

        Text(video.caption.ifBlank { "Без подписи" }, style = MaterialTheme.typography.headlineSmall)
        Text(
            text = "${video.date} · ${video.statsLine()}",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        SectionTitle("Чем смешно")
        Text(video.whyFunny, style = MaterialTheme.typography.bodyLarge)

        SectionTitle("О чём")
        Text(video.summary, style = MaterialTheme.typography.bodyLarge)

        if (video.quotes.isNotEmpty()) {
            SectionTitle("Что он говорит")
            video.quotes.forEach { quote ->
                Text("«$quote»", style = MaterialTheme.typography.titleMedium, fontStyle = FontStyle.Italic)
            }
        }

        SectionTitle("Что пишут в комментариях")
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            video.comments.forEach { CommentItem(it) }
        }

        if (video.url.isNotBlank()) {
            Button(
                onClick = { context.startActivity(Intent(Intent.ACTION_VIEW, video.url.toUri())) },
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Открыть в TikTok")
            }
        }
    }
}
