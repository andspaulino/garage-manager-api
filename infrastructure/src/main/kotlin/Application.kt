package io.github.andspaulino

import io.github.andspaulino.plugins.configureDatabases
import io.github.andspaulino.plugins.configureFrameworks
import io.github.andspaulino.plugins.configureMonitoring
import io.github.andspaulino.plugins.configureRouting
import io.github.andspaulino.plugins.configureSerialization
import io.github.andspaulino.plugins.configureStatusPages
import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    configureFrameworks()
    configureSerialization()
    configureMonitoring()
    configureRouting()
    configureStatusPages()
    configureDatabases()
}
