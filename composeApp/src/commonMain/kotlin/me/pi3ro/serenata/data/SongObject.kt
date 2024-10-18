package me.pi3ro.serenata.data

import kotlinx.serialization.Serializable

@Serializable
data class SongObject(
    val id: Int,
    val title: String,
    val artist: String,
    val poster: String,
    val songUrl: String? = null
)