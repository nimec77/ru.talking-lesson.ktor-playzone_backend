package ru.talking_lesson.database.tokens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Tokens: Table("tokens") {
  private val id = Tokens.varchar("id", 50)
  private val login = Tokens.varchar("login", 25)
  private val token = Tokens.varchar("token", 50)

  fun insert(tokenDTO: TokenDTO) {
    transaction {
      Tokens.insert {
        it[id] = tokenDTO.rowId
        it[login] = tokenDTO.login
        it[token] = tokenDTO.token
      }
    }
  }

  fun fetchToken(token: String): TokenDTO? {
    return try {
      transaction {
        Tokens.select { Tokens.token eq token }.map {
          TokenDTO(
            rowId = it[Tokens.id],
            login = it[login],
            token = it[Tokens.token]
          )
        }.first()
      }
    } catch (e: Exception) {
      null
    }
  }
}