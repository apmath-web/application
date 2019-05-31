package com.apmath.applications.application.v1.actions

import com.apmath.applications.application.v1.actions.models.Application
import com.apmath.applications.application.v1.actions.models.toApplicationDomain
import com.apmath.applications.application.v1.exceptions.BadRequestValidationException
import com.apmath.applications.application.v1.exceptions.NotFoundException
import com.apmath.applications.application.v1.validator.ApplicationBuilder
import com.apmath.applications.domain.exceptions.NoClientException
import com.apmath.applications.domain.services.ApplicationServiceInterface
import com.apmath.validation.simple.NullableValidator
import com.apmath.validation.simple.RequiredValidator
import io.ktor.application.ApplicationCall
import io.ktor.request.receive
import io.ktor.response.respond
import java.lang.Exception

suspend fun ApplicationCall.v1Create(applicationService: ApplicationServiceInterface, clientId: String) {

    val application = receive<Application>()

    application.clientId = clientId

    val validator = ApplicationBuilder()
        .prepend("clientId", RequiredValidator())
        .prepend("amount", RequiredValidator())
        .prepend("currency", RequiredValidator())
        .prepend("coBorrowers", RequiredValidator())
        .prepend("guarantors", RequiredValidator())
        .prepend("term", RequiredValidator())
        .build()

    if (!validator.validate(application)) {

        throw BadRequestValidationException(validator)

    }

    val applicationDomain = application.toApplicationDomain()

    val applicationId: Int =
        try {

            applicationService.add(applicationDomain)

        } catch (e: NoClientException) {
            throw NotFoundException("Client does not exist")
        }

    respond(mapOf("id" to applicationId))

}
