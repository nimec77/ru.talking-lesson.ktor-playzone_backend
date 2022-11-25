package ru.talking_lesson.utils

import ru.talking_lesson.database.tokens.Tokens

object TokenCheck {
  fun isTokenValid(token: String): Boolean = Tokens.fetchToken(token) != null

  fun isTokenAdmin(token: String): Boolean = token == "c8ca65c4-dfec-4c2f-885f-8ea01742145f"
}

