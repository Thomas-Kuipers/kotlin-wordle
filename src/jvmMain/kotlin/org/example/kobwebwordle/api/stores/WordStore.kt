package org.example.kobwebwordle.api.stores

import com.varabyte.kobweb.api.InitApi
import com.varabyte.kobweb.api.InitApiContext
import com.varabyte.kobweb.api.data.add
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.example.kobwebwordle.models.CharacterFeedback
import org.example.kobwebwordle.models.GuessFeedback
import java.io.File

@InitApi
fun initWordStore(ctx: InitApiContext) {
    ctx.data.add(WordStore())
}

class WordStore {
    private var words: Collection<String> = mutableListOf<String>("ooooo")
    private var currentWord: String = ""

    init {
        this.setWords()
        this.refresh()
    }

    private fun setWords() {
        val jsonString: String = File("./src/jvmMain/resources/words.json").readText(Charsets.UTF_8)
        this.words = Json.decodeFromString(jsonString)
    }

    fun refresh() {
        this.currentWord = this.words.random()
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