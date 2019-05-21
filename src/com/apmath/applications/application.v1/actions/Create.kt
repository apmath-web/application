package com.apmath.applications.application.v1.actions

import com.apmath.applications.application.v1.models.Application
import com.apmath.applications.application.v1.models.toApplicationClient
import com.apmath.applications.application.v1.respondError
import com.apmath.applications.application.v1.validator.ApplicationBuilder
import com.apmath.applications.domain.services.ApplicationServiceInterface
import com.apmath.validation.simple.RequiredValidator
import io.ktor.application.ApplicationCall
import io.ktor.request.receive
import io.ktor.response.respond
import java.lang.Exception

suspend fun ApplicationCall.v1Create(applicationService:ApplicationServiceInterface){

    val application = receive<Application>()

    application.clientId = getUserId(request)

    val validator = ApplicationBuilder()
        .prepend("clientId", RequiredValidator())
        .prepend("amount", RequiredValidator())
        .prepend("currency", RequiredValidator())
        .prepend("coBorrowers", RequiredValidator())
        .prepend("guarantors", RequiredValidator())
        .build()

    if (!validator.validate(application)){

        respond(validator.messages)
        return

    }

    val applicationDomain = application.toApplicationClient()

    val applicationId: Int =
            try{

                applicationService.add(applicationDomain)

            } catch (e:Exception){

                respondError(e)
                return

            }

    respond(mapOf("id" to applicationId))

}
