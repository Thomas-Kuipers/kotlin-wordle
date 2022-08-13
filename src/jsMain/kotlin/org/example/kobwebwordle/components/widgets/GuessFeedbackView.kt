package org.example.kobwebwordle.components.widgets

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import org.example.kobwebwordle.models.CharacterFeedback
import org.example.kobwebwordle.models.GuessFeedback
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable

@Composable

fun GuessFeedbackView (
    guessFeedback: GuessFeedback
) {
    val colors = mapOf<CharacterFeedback, CSSColorValue>(
        CharacterFeedback.CORRECT to Color("#75B24E"),
        CharacterFeedback.SEMI_CORRECT to Color("#EBBC35"),
        CharacterFeedback.INCORRECT to Color("#9FA9BE")
    )

    Div(attrs = {
        style {
            display(DisplayStyle.Flex)
        }
    }) {
        guessFeedback.feedback.indices.forEach {
            val feedback: CharacterFeedback = guessFeedback.feedback[it]
            val char: Char = guessFeedback.chars[it]

            Div(attrs = {
                style {
                    backgroundColor(colors[feedback]!!)
                    width(80.px)
                    height(80.px)
                    marginRight(2.px)
                    marginBottom(2.px)
                    borderRadius(4.px)
                    lineHeight(80.px)
                    textAlign("center")
                    borderWidth(0.px)
                    color(Color("white"))
                    fontSize(30.px)
                }
            }) {
                Text(char.toString())
            }
        }
    }
}