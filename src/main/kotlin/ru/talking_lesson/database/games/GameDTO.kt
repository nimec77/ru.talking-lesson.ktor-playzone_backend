package ru.talking_lesson.database.games

import kotlinx.serialization.Serializable
import ru.talking_lesson.features.games.models.*
import java.util.UUID

@Serializable
data class GameDTO(
  val gameId: String,
  val name: String,
  val backdrop: String?,
  val logo: String,
  val description: String,
  var downloadCount: Int,
  val version: String,
  val size: Int,
)

fun CreateGameRequest.mapToGameDTO(): GameDTO = GameDTO(
  gameId = UUID.randomUUID().toString(),
  name = name,
  backdrop = "",
  logo = "",
  description = description,
  downloadCount = 0,
  version = version,
  size = size,
)

fun GameDTO.mapToCreateGameResponse() = CreateGameResponse(
  gameId = gameId,
  name = name,
  description = description,
  version = version,
  size = size,
)
