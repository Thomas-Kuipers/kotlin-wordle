package org.example.kobwebwordle.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.launch
import org.example.kobwebwordle.components.layouts.PageLayout
import org.example.kobwebwordle.components.widgets.CharInput
import org.example.kobwebwordle.components.widgets.GuessFeedbackView
import org.example.kobwebwordle.components.widgets.NewGameButton
import org.example.kobwebwordle.models.CharacterFeedback
import org.example.kobwebwordle.models.GuessFeedback
import org.example.kobwebwordle.services.ApiClient
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLInputElement

@Page
@Composable
fun HomePage() {
    val chars = mutableStateListOf<String>("", "", "", "", "")
    val guessFeedbacks = mutableStateListOf<GuessFeedback>()
    val completed = mutableStateOf<Boolean>(false)
    val coroutineScope = rememberCoroutineScope()
    val apiClient = ApiClient(coroutineScope)

    fun focusInput(index: Int) {
        val input = document.getElementById("char-$index") as HTMLInputElement?

        if (input !== null) {
            input.focus()
        }
    }

    fun resetInputFields() {
        chars.indices.forEach { chars[it] = "" }
    }

    suspend fun sendGuess() {
        val guess = chars.fold("") { word, char -> "$word$char" }

        if (guess.length < 5) {
            return
        }

        val guessFeedback = apiClient.guess(guess)
        guessFeedbacks.add(guessFeedback)
        resetInputFields()
        focusInput(0)

        completed.value = !guessFeedback.feedback.contains(CharacterFeedback.INCORRECT) && !guessFeedback.feedback.contains(CharacterFeedback.SEMI_CORRECT)
    }

    suspend fun newGame() {
        apiClient.refresh()
        guessFeedbacks.clear()
        completed.value = false
        resetInputFields()

        window.setTimeout(fun () {
            // Give Compose some time to re-render the inputs, otherwise we can't focus them yet
            // Todo: detect when compose has actually finished rendering, rather than guessing the time it takes
            focusInput(0)
        }, 100)
    }

    PageLayout("Wordle!") {

        // Display all the previous guesses
        guessFeedbacks.forEach {
            GuessFeedbackView(it)
        }

        if (!completed.value) {
            Div () {
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
                            coroutineScope.launch {
                                sendGuess()
                            }
                        }
                    )
                }
            }
        } else {
            P (attrs = {
                style {
                    fontSize(16.px)
                    marginTop(20.px)
                    marginBottom(20.px)
                }
            }) {
                Text("Well done! In just ${guessFeedbacks.size} tries!")
            }
        }

        Footer () {
            NewGameButton(
                onClick = fun () {
                    coroutineScope.launch {
                        newGame()
                    }
                }
            )
        }
    }
}