package me.pi3ro.serenata.ui.screens.player

import MediaPlayer
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import me.pi3ro.serenata.data.SongObject
import me.pi3ro.serenata.ui.EmptyScreenContent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PlayerScreen(
    objectId: Int
) {
    val viewModel = koinViewModel<PlayerViewModel>()
    val obj by viewModel.getObject(objectId).collectAsState(initial = null)

    AnimatedContent(obj != null) { objectAvailable ->
        if (objectAvailable) {
            ObjectDetails(obj!!)
        } else {
            EmptyScreenContent(Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun ObjectDetails(
    obj: SongObject,
) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.height(60.dp))

                // Album Art Image
                KamelImage(
                    resource = asyncPainterResource(data = obj.poster),
                    contentDescription = obj.title,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .clip(RoundedCornerShape(12.dp))
                        .aspectRatio(1f)
                )

                // Song Title and Artist
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    Text(
                        text = obj.title,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = obj.artist,
                        fontSize = 16.sp,
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MediaPlayer(
                        modifier = Modifier.fillMaxWidth(),
                        url = obj.songUrl ?: "",
                        startTime = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        endTime = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        volumeIconColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        playIconColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        sliderTrackColor = Color.LightGray,
                        sliderIndicatorColor = if (isSystemInDarkTheme()) Color.White else Color.Black
                    )
                }
            }
        }
    }
}