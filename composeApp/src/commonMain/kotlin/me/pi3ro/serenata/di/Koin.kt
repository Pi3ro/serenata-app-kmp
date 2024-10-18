package me.pi3ro.serenata.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import me.pi3ro.serenata.data.InMemorySongStorage
import me.pi3ro.serenata.data.KtorSongAPI
import me.pi3ro.serenata.data.SongAPI
import me.pi3ro.serenata.data.SongRepository
import me.pi3ro.serenata.data.SongStorage
import me.pi3ro.serenata.ui.screens.song.SongViewModel
import me.pi3ro.serenata.ui.screens.player.PlayerViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }

        HttpClient {
            install(ContentNegotiation) {
                json(json, contentType = ContentType.Any)
            }
        }
    }

    single<SongAPI> {
        KtorSongAPI(get())
    }
    single<SongStorage> {
        InMemorySongStorage()
    }
    single {
        SongRepository(get(), get()).apply {
            init()
        }
    }
}

val viewModelModule = module {
    factoryOf(::SongViewModel)
    factoryOf(::PlayerViewModel)
}

fun initKoin()
{
    startKoin {
        modules(
            dataModule,
            viewModelModule,
        )
    }
}