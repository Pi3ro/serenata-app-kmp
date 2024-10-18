package me.pi3ro.serenata

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import me.pi3ro.serenata.ui.screens.PlayerDestination
import me.pi3ro.serenata.ui.screens.SongDestination
import me.pi3ro.serenata.ui.screens.player.PlayerScreen
import me.pi3ro.serenata.ui.screens.song.SongScreen

@Composable
fun NavigationWrapper()
{
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = SongDestination) {
        composable<SongDestination> {
            SongScreen(
                navigateToDetails = { objectId ->
                    navController.navigate(PlayerDestination(objectId))
                }
            )
        }

        composable<PlayerDestination> { backStackEntry ->
            PlayerScreen(
                objectId = backStackEntry.toRoute<PlayerDestination>().objectId,
            )
        }
    }
}