package org.example.kobwebwordle.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.setBodyText
import org.example.kobwebwordle.api.stores.WordStore

@Api
fun refresh(ctx: ApiContext) {
    ctx.data.getValue<WordStore>().refresh()

    ctx.res.setBodyText("done")
}