package ru.talking_lesson.cache

import ru.talking_lesson.features.register.RegisterReceiveRemote

data class TokenCache(
  val login: String,
  val token: String,
)

object InMemoryCache {
  val userList: MutableList<RegisterReceiveRemote> = mutableListOf()
  val tokenList: MutableList<TokenCache> = mutableListOf()
}