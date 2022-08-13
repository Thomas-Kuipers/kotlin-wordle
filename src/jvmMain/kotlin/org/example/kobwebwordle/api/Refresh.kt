package org.example.kobwebwordle.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.setBodyText
import todo.model.WordStore

@Api
fun refresh(ctx: ApiContext) {
    ctx.data.getValue<WordStore>().newWord()

    ctx.res.setBodyText("done")
}