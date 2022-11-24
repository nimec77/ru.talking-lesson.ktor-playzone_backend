package ru.talking_lesson.features.games.models

import kotlinx.serialization.Serializable

@Serializable
data class FetchGameRequest(
  val token: String,
  val searchQuery: String,
)