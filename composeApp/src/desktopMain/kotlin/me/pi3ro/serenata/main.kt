package me.pi3ro.serenata

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Serenata",
    ) {
        App()
    }
}