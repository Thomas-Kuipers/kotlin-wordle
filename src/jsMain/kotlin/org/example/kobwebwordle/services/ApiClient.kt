package org.example.kobwebwordle.services

import com.varabyte.kobweb.browser.ApiFetcher
import kotlinx.browser.window
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import org.example.kobwebwordle.models.GuessFeedback

class ApiClient {
    companion object {
        private fun post(path: String, body: String? = null) = GlobalScope.async {
            val fetcher = ApiFetcher()
            val result = fetcher.post(path, true, body?.encodeToByteArray())

            result?.decodeToString()
        }

        fun guess(guess: String): Deferred<GuessFeedback> = GlobalScope.async {
            val result = ApiClient.post("guess?guess=$guess").await() as String
            val guessFeedback = Json.decodeFromString<GuessFeedback>(result)

            guessFeedback
        }

        fun refresh() = GlobalScope.async {
            ApiClient.post("refresh").await()
        }
    }
}