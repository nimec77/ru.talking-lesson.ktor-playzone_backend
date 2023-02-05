package ru.talking_lesson.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import kotlinx.serialization.Serializable

fun Application.configureRouting() {

  routing {
    get("/") {
      call.respondText("Hello, World!")
    }
  }
}
