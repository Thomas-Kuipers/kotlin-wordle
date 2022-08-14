package org.example.kobwebwordle.components.widgets

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Text

@Composable

fun NewGameButton(onClick: () -> Any) {
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
        Text("New game")
    }
}