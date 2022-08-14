package org.example.kobwebwordle.api.stores

import com.varabyte.kobweb.api.InitApi
import com.varabyte.kobweb.api.InitApiContext
import com.varabyte.kobweb.api.data.add
import org.example.kobwebwordle.models.CharacterFeedback
import org.example.kobwebwordle.models.GuessFeedback

@InitApi
fun initWordStore(ctx: InitApiContext) {
    ctx.data.add(WordStore())
}

class WordStore {
    private var currentWord: String = ""

    init {
        this.refresh()
    }

    fun refresh() {
        val words = listOf<String>(
            "moose",
            "goose",
            "geese",
            "meese",
            "nerby",
            "patat",
            "spuit"
        )

        this.currentWord = words.asSequence().shuffled().find { true }!!
    }

    fun guess(guess: String): GuessFeedback {
        val guessUppercase = guess.uppercase()
        val correctWordUppercase = this.currentWord.uppercase()
        val guessChars = guessUppercase.toCharArray()
        val correctChars = correctWordUppercase.toCharArray()
        val result = mutableListOf<CharacterFeedback>()
        val chars = mutableListOf<Char>()

        for (i in correctChars.indices) {
            if (correctChars[i] == guessChars[i]) {
                result.add(CharacterFeedback.CORRECT)
                chars.add(guessChars[i])
            } else if (correctWordUppercase.contains(guessChars[i])) {
                result.add(CharacterFeedback.SEMI_CORRECT)
                chars.add(guessChars[i])
            } else {
                result.add(CharacterFeedback.INCORRECT)
                chars.add(guessChars[i])
            }
        }

        return GuessFeedback(result, chars)
    }
}