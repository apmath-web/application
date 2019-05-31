package com.apmath.applications.application.v1.exceptions

import io.ktor.http.HttpStatusCode

open class BadRequestException(message: String) : ApiException(message, HttpStatusCode.BadRequest)
