package com.ybanfan.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ybanfan.app.data.Comment
import com.ybanfan.app.data.Video
import com.ybanfan.app.ui.theme.Mustard
import java.util.Locale

private val russian = Locale.forLanguageTag("ru")

fun formatCount(value: Int): String = when {
    value >= 1_000_000 -> String.format(russian, "%.1f млн", value / 1_000_000.0)
    value >= 1_000 -> "${value / 1_000} тыс."
    else -> value.toString()
}

fun Video.statsLine(): String =
    "${formatCount(views)} просмотров · ${formatCount(likes)} лайков · ${rating} из 10"

@Composable
fun SectionTitle(text: String) {
    Column(Modifier.padding(top = 8.dp)) {
        Text(
            text = text.uppercase(russian),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
        )
        HorizontalDivider(
            modifier = Modifier.padding(top = 6.dp),
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
        )
    }
}

@Composable
fun DashList(items: List<String>) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items.forEach { item ->
            Row {
                Text("—", color = MaterialTheme.colorScheme.secondary, modifier = Modifier.width(24.dp))
                Text(item, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun CommentItem(comment: Comment) {
    Row(Modifier.height(IntrinsicSize.Min)) {
        Box(
            Modifier
                .width(3.dp)
                .fillMaxHeight()
                .background(Mustard),
        )
        Column(
            modifier = Modifier.padding(start = 12.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(comment.author, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.secondary)
                Text(formatCount(comment.likes), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Text(comment.text, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
