package ru.talking_lesson.features.games.models

import kotlinx.serialization.Serializable

@Serializable
data class CreateGameResponse(
  val gameId: String,
  val name: String,
  val description: String,
  val version: String,
  val size: Int,
)
