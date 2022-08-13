package org.example.kobwebwordle.components.widgets

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable

@Composable

fun CharInput (
    value: String = "",
    onChange: (char: String) -> Any
) {
    TextInput (attrs = {
        style {
            width(40.px)
            height(40.px)
            marginRight(2.px)
            borderRadius(4.px)
            lineHeight(40.px)
            textAlign("center")
            borderWidth(0.px)
            background("#eee")
            padding(0.px)
        }
        value(value)
        maxLength(1)
        onKeyDown {
            if (it.key === "Backspace") {
                onChange("")
            } else if (it.key.length == 1) {
                onChange(it.key)
            }
        }
    })
}