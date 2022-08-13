package todo.model

import com.varabyte.kobweb.api.InitApi
import com.varabyte.kobweb.api.InitApiContext
import com.varabyte.kobweb.api.data.add
import java.util.*
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

// NOTE: This is a simple demo, so we create an in-memory store (which will get reset everytime server code is live
// reloaded). However, in a production app, you should use a real database instead, maybe a redis backend, or possibly
// even something that lives on a different server which you access via network calls.

@InitApi
fun initTodoStore(ctx: InitApiContext) {
    ctx.data.add(WordStore())
}

class WordStore {
    private var currentWord: String = ""

    init {
        this.newWord()
    }

    fun getCurrentWord(): String {
        return this.currentWord
    }

    fun newWord() {
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
}