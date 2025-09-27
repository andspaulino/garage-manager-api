package io.github.andspaulino

import io.github.andspaulino.infrastructure.plugins.configureDatabases
import io.github.andspaulino.infrastructure.plugins.configureFrameworks
import io.github.andspaulino.infrastructure.plugins.configureMonitoring
import io.github.andspaulino.infrastructure.plugins.configureRouting
import io.github.andspaulino.infrastructure.plugins.configureSerialization
import io.github.andspaulino.infrastructure.plugins.configureStatusPages
import io.ktor.server.application.Application

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureFrameworks()
    configureSerialization()
    configureMonitoring()
    configureRouting()
    configureStatusPages()
    configureDatabases()
}
