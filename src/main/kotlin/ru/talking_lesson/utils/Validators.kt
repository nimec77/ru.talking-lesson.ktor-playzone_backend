package ru.talking_lesson.utils

fun String.isValidEmail() = this.matches(Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"))