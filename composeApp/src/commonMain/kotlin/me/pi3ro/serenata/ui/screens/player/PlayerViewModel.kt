package me.pi3ro.serenata.ui.screens.player

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import me.pi3ro.serenata.data.SongObject
import me.pi3ro.serenata.data.SongRepository

class PlayerViewModel(
  private val songRepository: SongRepository
) : ViewModel()
{
    fun getObject(objectId: Int): Flow<SongObject?> =
        songRepository.getObjectById(objectId)
}