package org.example.kobwebwordle.api.helpers

import org.example.kobwebwordle.models.CharacterFeedback
import org.example.kobwebwordle.models.GuessFeedback

fun getGuessFeedback(guess: String, correctWord: String): GuessFeedback {
    val guessChars = guess.toCharArray()
    val correctChars = correctWord.toCharArray()
    val result = mutableListOf<CharacterFeedback>()

    for (i in correctChars.indices) {
        if (correctChars[i] == guessChars[i]) {
            result.add(CharacterFeedback.CORRECT)
        } else if (correctWord.contains(guessChars[i])) {
            result.add(CharacterFeedback.SEMI_CORRECT)
        } else {
            result.add(CharacterFeedback.INCORRECT)
        }
    }

    return GuessFeedback(result)
}