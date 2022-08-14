package org.example.kobwebwordle.services

import com.varabyte.kobweb.browser.ApiFetcher
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import org.example.kobwebwordle.models.GuessFeedback

class ApiClient(private val coroutineScope: CoroutineScope) {
    private suspend fun post(path: String, body: String? = null): String? {
        val fetcher = ApiFetcher()
        val result = fetcher.post(path, true, body?.encodeToByteArray())

        return result?.decodeToString()
    }

    suspend fun guess(guess: String): GuessFeedback {
        val result = coroutineScope.async { this@ApiClient.post("guess?guess=$guess") }.await()!!
        return coroutineScope.async { Json.decodeFromString<GuessFeedback>(result) }.await()
    }

    suspend fun refresh() {
        coroutineScope.async { this@ApiClient.post("refresh") }.await()
    }
}