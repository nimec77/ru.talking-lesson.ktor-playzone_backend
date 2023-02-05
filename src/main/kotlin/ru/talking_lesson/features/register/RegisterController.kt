package ru.talking_lesson.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import ru.talking_lesson.database.tokens.TokenDTO
import ru.talking_lesson.database.tokens.Tokens
import ru.talking_lesson.database.users.UserDTO
import ru.talking_lesson.database.users.Users
import ru.talking_lesson.utils.isValidEmail
import java.util.UUID

class RegisterController(private val call: ApplicationCall) {
  suspend fun registerNewUser() {
    val registerResponseRemote = call.receive<RegisterReceiveRemote>()
    if (!registerResponseRemote.email.isValidEmail()) {
      call.respond(HttpStatusCode.BadRequest, "Email is not valid")
    }
    val isUserExist = Users.fetchUser(registerResponseRemote.login) != null
    if (isUserExist) {
      call.respond(HttpStatusCode.Conflict, "User already exist")
    } else {
      Users.insert(
        UserDTO(
          login = registerResponseRemote.login,
          password = registerResponseRemote.password,
          userName = "",
          email = registerResponseRemote.email
        )
      )
      val token = UUID.randomUUID().toString()
      Tokens.insert(
        tokenDTO = TokenDTO(
          rowId = UUID.randomUUID().toString(),
          login = registerResponseRemote.login,
          token = token
        )
      )
      call.respond(RegisterResponseRemote(token = token))
    }
  }
}