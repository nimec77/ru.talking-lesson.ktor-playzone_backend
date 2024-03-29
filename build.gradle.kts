val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposed_version: String by project

plugins {
    application
    kotlin("jvm") version "1.7.21"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.21"
}

group = "ru.talking-lesson"
version = "0.0.1"
application {
    mainClass.set("ru.talking_lesson.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {

  implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.postgresql:postgresql:42.5.0")

    implementation("ch.qos.logback:logback-classic:$logback_version")
  implementation("io.ktor:ktor-server-core-jvm:2.2.2")
  implementation("io.ktor:ktor-server-content-negotiation:2.2.2")
  implementation("io.ktor:ktor-server-cio-jvm:2.2.2")
  implementation("io.ktor:ktor-serialization-kotlinx-json:2.2.2")
  implementation("io.ktor:ktor-server-test-host-jvm:2.2.2")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
  testImplementation("io.ktor:ktor-server-tests-jvm:2.2.2")
}