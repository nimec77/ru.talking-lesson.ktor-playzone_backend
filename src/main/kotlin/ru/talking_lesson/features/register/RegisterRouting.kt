package ru.talking_lesson.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.talking_lesson.cache.InMemoryCache
import ru.talking_lesson.cache.TokenCache
import ru.talking_lesson.utils.isValidEmail
import java.util.*

fun Application.configureRegisterRouting() {

  routing {
    post("/register") {
      val registerController = RegisterController(call)
      registerController.registerNewUser()
    }
  }
}
