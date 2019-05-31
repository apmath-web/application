package com.apmath.applications.application.v1.exceptions

import io.ktor.http.HttpStatusCode

abstract class ApiException(
    message: String,
    val status: HttpStatusCode
) : Exception(message)
