import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain
import plugins.configureDatabases
import plugins.configureFrameworks
import plugins.configureMonitoring
import plugins.configureRouting
import plugins.configureSerialization
import plugins.configureStatusPages

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
