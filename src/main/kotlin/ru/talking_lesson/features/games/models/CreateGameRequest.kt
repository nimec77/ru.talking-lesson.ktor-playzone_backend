package ru.talking_lesson.features.games.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateGameRequest(
    val name: String,
    val description: String,
    val version: String,
    val size: Int,
)
