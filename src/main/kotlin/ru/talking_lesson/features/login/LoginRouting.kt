package ru.talking_lesson.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.talking_lesson.cache.InMemoryCache
import ru.talking_lesson.cache.TokenCache
import java.util.UUID

fun Application.configureLoginRouting() {

  routing {
    post("/login") {
      val receive = call.receive<LoginReceiveRemote>()
      val userLogin = InMemoryCache.userList.firstOrNull { it.login == receive.login }
      if (userLogin == null) {
        call.respond(HttpStatusCode.NotFound, "User not found")
      } else {
        if (userLogin.password != receive.password) {
          call.respond(HttpStatusCode.Unauthorized, "Password is incorrect")
        }
        val token = UUID.randomUUID().toString()
        InMemoryCache.tokenList.add(TokenCache(login = receive.login, token = token))
        call.respond(LoginResponseRemote(token = token))
      }
    }
  }
}
