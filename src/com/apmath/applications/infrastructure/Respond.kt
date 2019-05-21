package com.apmath.applications.infrastructure

import com.apmath.applications.application.v1.exceptions.ApiException
import com.apmath.applications.application.v1.exceptions.BadRequestValidationException
import com.apmath.validation.PathMessageInterface
import io.ktor.application.ApplicationCall
import io.ktor.response.respond

suspend fun respondApiException(call: ApplicationCall, e: ApiException) {
    when {
        e is BadRequestValidationException -> {

            val description: HashMap<String, String> = HashMap()
            e.messages.forEach {
                if (it is PathMessageInterface) {
                    description[it.path] = it.message
                }
            }

            call.respond(
                e.status,
                mapOf("message" to e.message!!, "description" to description)
            )
        }
        e.message != null -> call.respond(e.status,  mapOf("message" to e.message!!))
        else -> call.respond(e.status, mapOf("message" to e.javaClass))
    }
}
