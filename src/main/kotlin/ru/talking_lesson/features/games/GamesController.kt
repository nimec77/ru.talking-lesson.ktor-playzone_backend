package ru.talking_lesson.features.games

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.talking_lesson.database.games.Games
import ru.talking_lesson.database.games.mapToGameDTO
import ru.talking_lesson.features.games.models.*
import ru.talking_lesson.utils.TokenCheck

class GamesController(private val call:ApplicationCall) {

  suspend fun performSearch() {
    val request = call.receive<FetchGameRequest>()
    val token = call.request.headers["Bearer-Authorization"]

    if (TokenCheck.isTokenValid(token.orEmpty()) || TokenCheck.isTokenAdmin(token.orEmpty())) {
      call.respond(Games.fetchGamesByName(request.searchQuery))
    } else {
      call.respond(HttpStatusCode.Unauthorized, "Token expired")
    }
  }

  suspend fun createGame() {
    val token = call.request.headers["Bearer-Authorization"]
    if (TokenCheck.isTokenAdmin(token.orEmpty())) {
      val request = call.receive<CreateGameRequest>()
      val game = request.mapToGameDTO()
    } else {
      call.respond(HttpStatusCode.Unauthorized, "Token expired")
    }
  }

}