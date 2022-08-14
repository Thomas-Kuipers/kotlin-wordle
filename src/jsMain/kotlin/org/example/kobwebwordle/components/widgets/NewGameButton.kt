package org.example.kobwebwordle.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.browser.document
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.events.Event
import org.w3c.dom.events.KeyboardEvent

@Composable

fun NewGameButton(onClick: () -> Any, completed: Boolean) {
    val onKeyDown: ((Event) -> Unit) = { event ->
        val keyboardEvent = event as KeyboardEvent

        if (keyboardEvent.key === "Enter") {
            onClick()
        }
    }

    LaunchedEffect(completed) {
        if (completed) {
            document.addEventListener("keydown", onKeyDown)
        } else {
            document.removeEventListener("keydown", onKeyDown)
        }
    }

    var text = "New game"

    if (completed) {
        text += " [press enter]"
    }

    Button(attrs = {
        style {
            background("none")
            borderWidth(0.px)
            marginTop(20.px)
            fontSize(16.px)
            cursor("pointer")
            color(Color("#2DA2BB"))
            textDecoration("underline")
        }
        onClick {
            onClick()
        }
    }) {
        Text(text)
    }
}