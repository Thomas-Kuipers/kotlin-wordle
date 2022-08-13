package org.example.kobwebwordle.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.browser.ApiFetcher
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.text.Text
import kotlinx.browser.document
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
import org.w3c.dom.HTMLInputElement

@Page
@Composable
fun HomePage() {
    val chars = mutableStateListOf<String>("a", "a", "a", "a", "a")
    val guessFeedbacks = mutableStateListOf<GuessFeedback>()

    fun focusInput(index: Int) {
        val input = document.getElementById("char-$index") as HTMLInputElement?

        if (input !== null) {
            input.focus()
        }
    }

    fun sendGuess() {
        GlobalScope.launch {
            val guess = chars.fold("") { word, char -> "$word$char" }
            val guessFeedback = ApiClient.guess(guess).await()

            guessFeedbacks.add(guessFeedback)

            // Reset the input fields
            chars.indices.forEach { chars[it] = "" }

            focusInput(0)
        }
    }

    PageLayout("Wordle!") {

        // Display all the previous guesses
        guessFeedbacks.forEach {
            GuessFeedbackView(it)
        }

        // Display the 5 input fields (1 per character)
        chars.indices.forEach {
            CharInput(
                index = it,
                value = chars[it],
                focus = it == 0,
                onChange = fun (char) {
                    chars[it] = char
                },
                onNext = fun() {
                    if (it < (chars.size - 1)) {
                        focusInput(it + 1)
                    }
                },
                onPrev = fun() {
                    if (it > 0) {
                        focusInput(it - 1)
                    }
                },
                onSubmit = fun() {
                    sendGuess()
                }
            )
        }
    }
}