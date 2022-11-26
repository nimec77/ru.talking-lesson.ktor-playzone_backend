package ru.talking_lesson

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.testing.*
import ru.talking_lesson.features.games.configureGamesRouting
import ru.talking_lesson.features.games.models.FetchGameRequest
import ru.talking_lesson.plugins.configureRouting
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
  @Test
  fun testRoot() = testApplication {
    application {
      configureRouting()
    }
    client.get("/").apply {
      assertEquals(HttpStatusCode.OK, status)
      assertEquals("Hello, World!", bodyAsText())
    }
  }

    @Test
    fun testGamesSearch() = testApplication {
        install(ContentNegotiation) {
            json()
        }
        application {
            configureGamesRouting()
        }
        client.post("/games/search") {
            contentType(ContentType.Application.Json)
            setBody(FetchGameRequest(searchQuery = "test"))
        }.apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello, World!", bodyAsText())
        }
    }

//  @Test
//  fun testGamesSearch() = testApplication {
//    val client = createClient {
//      install(ContentNegotiation) {
//        json()
//      }
//    }
//    val response = client.post("/games/search") {
//      contentType(ContentType.Application.Json)
//      setBody(FetchGameRequest(searchQuery = "test"))
//    }
//    assertEquals(HttpStatusCode.OK, response.status)
//  }
}
