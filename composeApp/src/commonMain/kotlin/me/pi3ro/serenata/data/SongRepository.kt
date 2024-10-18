package me.pi3ro.serenata.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SongRepository(
    private val songAPI: SongAPI,
    private val songStorage: SongStorage
) {
    private val scope = CoroutineScope(SupervisorJob())

    fun init()
    {
        scope.launch {
            refresh()
        }
    }

    private suspend fun refresh()
    {
        songStorage.saveObjects(songAPI.getData())
    }

    fun getObjects(): Flow<List<SongObject>> = songStorage.getObjects()
    fun getObjectById(objectId: Int): Flow<SongObject?> = songStorage.getObjectById(objectId)
}