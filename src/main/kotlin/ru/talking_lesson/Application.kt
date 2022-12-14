package ru.talking_lesson

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import org.jetbrains.exposed.sql.Database
import ru.talking_lesson.features.games.configureGamesRouting
import ru.talking_lesson.features.login.configureLoginRouting
import ru.talking_lesson.features.register.configureRegisterRouting
import ru.talking_lesson.plugins.*

fun main() {
  Database.connect(
    "jdbc:postgresql://192.168.0.100:5432/playzone",
    driver = "org.postgresql.Driver",
    user = "postgres",
    password = "postgres"
  )
  embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
    .start(wait = true)
}

fun Application.module() {
  configureRouting()
  configureSerialization()
  configureLoginRouting()
  configureRegisterRouting()
  configureGamesRouting()
}
