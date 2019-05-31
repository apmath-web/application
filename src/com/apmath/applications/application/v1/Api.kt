package com.apmath.applications.application.v1

import com.apmath.applications.application.v1.actions.v1Create
import com.apmath.applications.application.v1.actions.v1Info
import com.apmath.applications.application.v1.actions.v1ListApplications
import com.apmath.applications.domain.services.ApplicationServiceInterface
import com.apmath.applications.application.v1.actions.v1LoanRequest
import com.apmath.applications.application.v1.exceptions.ApiException
import com.apmath.applications.application.v1.exceptions.BadRequestValidationException
import com.apmath.validation.PathMessageInterface
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import org.koin.ktor.ext.inject

internal fun Routing.v1() {

    v1Info()
    v1Application()
    // other route groups here
}

private fun Routing.v1Info() {

    route("v1") {
        get("info") {
            call.v1Info()
        }

    }
}

private fun Routing.v1Application() {

    val applicationService: ApplicationServiceInterface by inject()

    route("v1") {
        get("{applicationId}") {
            val parameters = call.parameters
            val application = applicationService.get(parameters["applicationId"]!!.toInt())
            call.respond(application)
        }
        post("{clientId}/{applicationId}") {
            val parameters = call.parameters
            call.v1LoanRequest(parameters["clientId"]!!, parameters["applicationId"]!!)
        }
        post("{clientId}") {
            val parameters = call.parameters
            call.v1Create(applicationService, parameters["clientId"]!!)
        }
    }
}


