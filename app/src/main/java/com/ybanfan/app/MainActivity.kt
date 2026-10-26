package com.ybanfan.app

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ybanfan.app.data.ContentRepository
import com.ybanfan.app.ui.YbanApp
import com.ybanfan.app.ui.theme.YbanTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lightBars = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        enableEdgeToEdge(statusBarStyle = lightBars, navigationBarStyle = lightBars)
        val repository = ContentRepository(this)
        setContent {
            YbanTheme { YbanApp(repository) }
        }
    }
}
