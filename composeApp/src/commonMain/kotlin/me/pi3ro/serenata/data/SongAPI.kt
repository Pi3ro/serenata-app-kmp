package me.pi3ro.serenata.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.util.concurrent.CancellationException

interface SongAPI
{
    suspend fun getData(): List<SongObject>
}

@Serializable
data class SongListResponse(
    val songs: List<SongObject>
)

class KtorSongAPI(
    private val client: HttpClient
) : SongAPI
{
    companion object
    {
        private const val BASE_URL = "https://serenata-7b414-default-rtdb.firebaseio.com/.json"
    }

    override suspend fun getData(): List<SongObject>
    {
        return try {
            val response = client.get(BASE_URL).body<String>()
            val songListResponse = Json.decodeFromString<SongListResponse>(response)
            songListResponse.songs
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()

            emptyList()
        }
    }
}