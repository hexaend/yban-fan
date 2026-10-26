package com.ybanfan.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ybanfan.app.data.Video

@Composable
fun CommentsScreen(videos: List<Video>, onVideoClick: (String) -> Unit) {
    val commentsWithVideo = remember(videos) {
        videos
            .flatMap { video -> video.comments.map { comment -> video to comment } }
            .sortedByDescending { (_, comment) -> comment.likes }
    }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            Text("Что пишут Ыбанисты", style = MaterialTheme.typography.headlineSmall)
        }
        items(commentsWithVideo) { (video, comment) ->
            Column(
                modifier = Modifier.clickable { onVideoClick(video.id) },
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                CommentItem(comment)
                Text(
                    text = video.caption.ifBlank { video.id },
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 15.dp),
                )
            }
        }
    }
}
