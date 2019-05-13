package com.apmath.applications.application.v1.actions

import io.ktor.request.ApplicationRequest
import java.lang.NumberFormatException

fun getUserId(request: ApplicationRequest): Int? {

    val userHeaderKey = "clientId"

    if (request.headers.contains(userHeaderKey)){

        try {

            return request.headers[userHeaderKey]?.toInt()

        } catch (e: NumberFormatException){

        }

    }

    return null

}
