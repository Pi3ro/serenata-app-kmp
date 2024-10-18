package me.pi3ro.serenata.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

interface SongStorage
{
    suspend fun saveObjects(newObjects: List<SongObject>)
    fun getObjectById(objectId: Int): Flow<SongObject?>
    fun getObjects(): Flow<List<SongObject>>
}

class InMemorySongStorage : SongStorage
{
    private val storedObjects = MutableStateFlow(emptyList<SongObject>())

    override suspend fun saveObjects(newObjects: List<SongObject>)
    {
        storedObjects.value = newObjects
    }

    override fun getObjectById(objectId: Int): Flow<SongObject?>
    {
        return storedObjects.map { objects ->
            objects.find { it.id == objectId }
        }
    }

    override fun getObjects(): Flow<List<SongObject>>
    {
        return storedObjects
    }
}