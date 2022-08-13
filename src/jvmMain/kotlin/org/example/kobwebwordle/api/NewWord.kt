package org.example.kobwebwordle.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.setBodyText
import org.example.kobwebwordle.api.helpers.getGuessFeedback
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import todo.model.WordStore

@Api
fun newWord(ctx: ApiContext) {
    ctx.data.getValue<WordStore>().newWord()
}