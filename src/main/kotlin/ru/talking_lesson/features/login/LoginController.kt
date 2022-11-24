package ru.talking_lesson.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.talking_lesson.database.tokens.TokenDTO
import ru.talking_lesson.database.tokens.Tokens
import ru.talking_lesson.database.users.Users
import java.util.*

class LoginController(private val  call: ApplicationCall) {
  suspend fun performLogin() {
    val receive = call.receive<LoginReceiveRemote>()
    val userDTO = Users.fetchUser(receive.login)

    if (userDTO == null) {
      call.respond(HttpStatusCode.NotFound, "User not found")
    } else {
      if (userDTO.password != receive.password) {
        call.respond(HttpStatusCode.Unauthorized, "Password is incorrect")
      }
      val token = UUID.randomUUID().toString()
      Tokens.insert(
        tokenDTO = TokenDTO(
          rowId = UUID.randomUUID().toString(),
          login = receive.login,
          token = token
        )
      )
      call.respond(LoginResponseRemote(token = token))
    }
  }
}