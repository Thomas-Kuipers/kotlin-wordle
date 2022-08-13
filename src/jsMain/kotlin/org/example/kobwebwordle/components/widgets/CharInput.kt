package org.example.kobwebwordle.components.widgets

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Composable

fun CharInput (
    index: Int,
    value: String,
    focus: Boolean,
    onChange: (char: String) -> Any,
    onNext: () -> Any,
    onPrev: () -> Any,
    onSubmit: () -> Unit
) {
    TextInput (attrs = {
        style {
            width(80.px)
            height(80.px)
            marginRight(2.px)
            borderRadius(4.px)
            lineHeight(80.px)
            textAlign("center")
            borderWidth(0.px)
            background("#eee")
            padding(0.px)
            fontSize(30.px)
        }
        id("char-$index")
        value(value)
        maxLength(1)
        onKeyDown {
            if (it.key === "Backspace" || it.key === "Delete") {
                onChange("")
                // Move to previous input field when user deletes a character
                onPrev()
            } else if (it.key === "ArrowLeft") {
                onPrev()
            } else if (it.key === "ArrowRight") {
                onNext()
            } else if (it.key === "Enter") {
                onSubmit()
            } else if (it.key.length == 1) {
                onChange(it.key)
                // Move to next input field when user enters a character
                onNext()
            }
        }
        if (focus) autoFocus()
        autoComplete(AutoComplete.off)
    })
}