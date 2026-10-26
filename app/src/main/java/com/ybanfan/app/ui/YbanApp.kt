package com.ybanfan.app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ybanfan.app.VideoActivity
import com.ybanfan.app.data.ContentRepository
import com.ybanfan.app.ui.screens.CommentsScreen
import com.ybanfan.app.ui.screens.HomeScreen
import com.ybanfan.app.ui.screens.TopVideosScreen
import com.ybanfan.app.ui.theme.Mustard

private enum class Destination(val route: String, val label: String, val icon: ImageVector) {
    Portrait("portrait", "Портрет", Icons.Filled.Home),
    TopVideos("top_videos", "Топ видео", Icons.Filled.Star),
    Comments("comments", "Ыбанисты", Icons.Filled.Favorite),
}

@Composable
fun YbanApp(repository: ContentRepository) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val context = LocalContext.current

    fun openVideo(id: String) = context.startActivity(VideoActivity.createIntent(context, id))

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceVariant) {
                Destination.entries.forEach { destination ->
                    NavigationBarItem(
                        selected = currentRoute == destination.route,
                        onClick = {
                            navController.navigate(destination.route) {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(destination.icon, contentDescription = destination.label) },
                        label = { Text(destination.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = Mustard.copy(alpha = 0.35f),
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        ),
                    )
                }
            }
        },
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Destination.Portrait.route,
            modifier = Modifier.padding(padding),
        ) {
            composable(Destination.Portrait.route) {
                HomeScreen(
                    portrait = repository.portrait,
                    onOpenTopVideos = { navController.navigate(Destination.TopVideos.route) },
                )
            }
            composable(Destination.TopVideos.route) {
                TopVideosScreen(repository.videos, onVideoClick = ::openVideo)
            }
            composable(Destination.Comments.route) {
                CommentsScreen(repository.videos, onVideoClick = ::openVideo)
            }
        }
    }
}
