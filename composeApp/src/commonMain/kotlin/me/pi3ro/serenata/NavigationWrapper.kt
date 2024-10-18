package me.pi3ro.serenata

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import me.pi3ro.serenata.ui.screens.player.PlayerScreen
import me.pi3ro.serenata.ui.screens.song.SongScreen

@Composable
fun NavigationWrapper(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = "songs") {
        composable("songs") {
            SongScreen(
                navigateToDetails = { objectId ->
                    navHostController.navigate("player/$objectId")
                }
            )
        }

        composable("player/{objectId}") { backStackEntry ->
            val objectId = backStackEntry.arguments?.getString("objectId")?.toIntOrNull()
            if (objectId != null) {
                PlayerScreen(objectId = objectId)
            }
        }
    }
}