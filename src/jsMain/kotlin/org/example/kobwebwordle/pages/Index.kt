package org.example.kobwebwordle.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.browser.ApiFetcher
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.text.Text
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.example.kobwebwordle.components.layouts.PageLayout
import org.example.kobwebwordle.components.widgets.CharInput
import org.example.kobwebwordle.components.widgets.GuessFeedbackView
import org.example.kobwebwordle.models.GuessFeedback
import org.example.kobwebwordle.services.ApiClient
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.P

@Page
@Composable
fun HomePage() {
    val chars = mutableStateListOf<String>("a", "a", "a", "a", "a")
    val guessFeedbacks = mutableStateListOf<GuessFeedback>()

    fun sendGuess() {
        GlobalScope.launch {
            val guess = chars.fold("") { word, char -> "$word$char" }
            val guessFeedback = ApiClient.guess(guess).await()

            console.log("add", guessFeedback.feedback)
            guessFeedbacks.add(guessFeedback)
        }
    }

    PageLayout("Wordle!") {

        guessFeedbacks.forEach {
            GuessFeedbackView(it)
        }

        chars.indices.forEach {
            CharInput(chars[it], fun (char) {
                chars[it] = char
            })
        }

        Button (attrs = {
            onClick {
                sendGuess()
            }
        }) {
            org.jetbrains.compose.web.dom.Text("Submit")
        }
    }
}