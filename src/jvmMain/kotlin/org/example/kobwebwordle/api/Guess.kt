package org.example.kobwebwordle.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import org.example.kobwebwordle.api.stores.WordStore

@Api
fun guess(ctx: ApiContext) {
    val guess = ctx.req.query["guess"] as String

    if (guess.length == 5) {
        val guessFeedback = ctx.data.getValue<WordStore>().guess(guess)
        ctx.res.setBodyText(Json.encodeToString(guessFeedback))
    } else {
        ctx.res.setBodyText("Invalid guess")
    }
}