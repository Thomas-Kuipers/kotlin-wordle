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
    Div() {
        guessFeedback.feedback.forEach {
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