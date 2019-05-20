package com.apmath.applications.application.v1.exceptions

import io.ktor.http.HttpStatusCode

abstract class ApiException(status: HttpStatusCode, message: String) : Exception(message) {
    val status: HttpStatusCode = status
}
