package org.example.kobwebwordle.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.setBodyText
import org.example.kobwebwordle.api.helpers.getGuessFeedback
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

@Api
fun guess(ctx: ApiContext) {
    val guess = ctx.req.query["guess"] as String
    val correctWord = "moose"
    val guessFeedback = getGuessFeedback(guess, correctWord)

    ctx.res.setBodyText(Json.encodeToString(guessFeedback))
}