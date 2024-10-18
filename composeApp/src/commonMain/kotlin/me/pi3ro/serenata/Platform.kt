package me.pi3ro.serenata

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform