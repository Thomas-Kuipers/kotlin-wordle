package org.example.kobwebwordle.models

import kotlinx.serialization.Serializable

@Serializable()

data class GuessFeedback(
    var feedback: List<CharacterFeedback>,
    var chars: List<Char>
)