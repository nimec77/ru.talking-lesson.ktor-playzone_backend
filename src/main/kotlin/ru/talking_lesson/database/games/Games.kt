package ru.talking_lesson.database.games

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.lowerCase
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Games : Table("games") {
  private val gameId = Games.varchar("game_id", 100)
  private val name = Games.varchar("name", 100)
  private val backdrop = Games.varchar("backdrop", 50).nullable()
  private val logo = Games.varchar("logo", 50)
  private val description = Games.varchar("description", 500)
  private val downloadCount = Games.integer("download_count")
  private val version = Games.varchar("version", 15)
  private val size = Games.integer("size")

  fun insert(gameDTO: GameDTO) {
    transaction {
      Games.insert {
        it[gameId] = gameDTO.gameId
        it[name] = gameDTO.name
        it[backdrop] = gameDTO.backdrop
        it[logo] = gameDTO.logo
        it[description] = gameDTO.description
        it[downloadCount] = gameDTO.downloadCount
        it[version] = gameDTO.version
        it[size] = gameDTO.size
      }
    }
  }

  fun fetchGamesByName(gameName: String): List<GameDTO> {
    return try {
      transaction {
        val searchString = if (gameName.trim().isEmpty()) {
          "%"
        } else {
          "%$gameName%".lowercase()
        }
        Games.select { name.lowerCase() like searchString }.map {
          GameDTO(
            gameId = it[gameId],
            name = it[name],
            backdrop = it[backdrop],
            logo = it[logo],
            description = it[description],
            downloadCount = it[downloadCount],
            version = it[version],
            size = it[size]
          )
        }
      }
    } catch (e: Exception) {
      emptyList()
    }
  }
}