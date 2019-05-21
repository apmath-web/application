package com.apmath.applications.application.v1

import com.apmath.applications.application.v1.actions.v1Create
import com.apmath.applications.application.v1.actions.v1Info
import com.apmath.applications.domain.exceptions.ApiException
import com.apmath.applications.domain.services.ApplicationServiceInterface
import io.ktor.application.ApplicationCall
import com.apmath.applications.application.v1.actions.v1LoanRequest
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.util.error
import org.koin.ktor.ext.inject

internal fun Routing.v1() {

    v1Info()
    // other route groups here
}

private fun Routing.v1Info() {

    val applicationService: ApplicationServiceInterface by inject()


    route("v1") {
        get("info") {
            call.v1Info()
        }
        post("{clientId}") {
            call.v1Create(applicationService)
        }
    }
}

suspend fun ApplicationCall.respondError(e: Exception) {

    application.environment.log.error(e)
    if (e is ApiException) {
        respond(e.code, e.message)
    } else {
        respond(HttpStatusCode.InternalServerError, "Something went wrong")
    }

}

private fun Routing.v1Application() {

    route("v1") {
        post("{clientId}/{applicationId}") {
            val parameters = call.parameters
            call.v1LoanRequest(parameters["clientId"]!!, parameters["applicationId"]!!)
        }
    }
}
