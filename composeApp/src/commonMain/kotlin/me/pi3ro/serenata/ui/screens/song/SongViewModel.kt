package me.pi3ro.serenata.ui.screens.song

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import me.pi3ro.serenata.data.SongObject
import me.pi3ro.serenata.data.SongRepository

class SongViewModel(
    songRepository: SongRepository
) : ViewModel()
{
    val objects: StateFlow<List<SongObject>> =
        songRepository.getObjects()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}