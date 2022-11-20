package ru.talking_lesson.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Users: Table("users") {
  private val login = Users.varchar("login", 25)
  private val password = Users.varchar("password", 25)
  private val userName = Users.varchar("username", 30)
  private val email = Users.varchar("email", 25)

  fun insert(userDTO: UserDTO) {
    transaction {
      Users.insert {
        it[login] = userDTO.login
        it[password] = userDTO.password
        it[userName] = userDTO.userName
        it[email] = userDTO.email ?: ""
      }
    }
  }

  fun fetchUser(login: String): UserDTO? {
    return try {
      val userModel = Users.select { Users.login eq login }.first()
      UserDTO(
        login = userModel[Users.login],
        password = userModel[password],
        userName = userModel[userName],
        email = userModel[email]
      )
    } catch (e: Exception) {
      null
    }
  }
}