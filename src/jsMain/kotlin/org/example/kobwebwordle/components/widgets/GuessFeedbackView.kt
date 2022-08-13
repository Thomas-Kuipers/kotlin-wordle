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
        guessFeedback.feedback.forEach {


            Div(attrs = {
                style {
                    backgroundColor(colors[it]!!)
                    width(40.px)
                    height(40.px)
                    marginRight(2.px)
                    marginBottom(2.px)
                    borderRadius(4.px)
                    lineHeight(40.px)
                    textAlign("center")
                    borderWidth(0.px)
                    color(Color("white"))

                }
            }) {
                if (it === CharacterFeedback.CORRECT) {
                    Text("Correct")
                } else if (it === CharacterFeedback.SEMI_CORRECT) {
                    Text("Semi correct")
                } else if (it === CharacterFeedback.INCORRECT) {
                    Text("Incorrect")
                }
            }


        }
    }
}