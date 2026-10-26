package com.ybanfan.app

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import com.ybanfan.app.data.ContentRepository
import com.ybanfan.app.ui.screens.VideoScreen
import com.ybanfan.app.ui.theme.YbanTheme

class VideoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lightBars = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        enableEdgeToEdge(statusBarStyle = lightBars, navigationBarStyle = lightBars)

        val videoId = requireNotNull(intent.getStringExtra(EXTRA_VIDEO_ID))
        val video = ContentRepository(this).videoById(videoId)

        setContent {
            YbanTheme {
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .statusBarsPadding()
                        .navigationBarsPadding(),
                ) {
                    VideoScreen(video, onBack = ::finish)
                }
            }
        }
    }

    companion object {
        private const val EXTRA_VIDEO_ID = "com.ybanfan.app.extra.VIDEO_ID"

        fun createIntent(context: Context, videoId: String): Intent =
            Intent(context, VideoActivity::class.java).putExtra(EXTRA_VIDEO_ID, videoId)
    }
}
