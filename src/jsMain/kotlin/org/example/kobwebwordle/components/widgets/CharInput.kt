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