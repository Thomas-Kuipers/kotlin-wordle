package org.example.kobwebwordle.api.helpers

import org.example.kobwebwordle.models.CharacterFeedback
import org.example.kobwebwordle.models.GuessFeedback

fun getGuessFeedback(guess: String, correctWord: String): GuessFeedback {
    val guessChars = guess.uppercase().toCharArray()
    val correctChars = correctWord.uppercase().toCharArray()
    val result = mutableListOf<CharacterFeedback>()
    val chars = mutableListOf<Char>()

    for (i in correctChars.indices) {
        if (correctChars[i] == guessChars[i]) {
            result.add(CharacterFeedback.CORRECT)
            chars.add(guessChars[i])
        } else if (correctWord.contains(guessChars[i])) {
            result.add(CharacterFeedback.SEMI_CORRECT)
            chars.add(guessChars[i])
        } else {
            result.add(CharacterFeedback.INCORRECT)
            chars.add(guessChars[i])
        }
    }

    return GuessFeedback(result, chars)
}