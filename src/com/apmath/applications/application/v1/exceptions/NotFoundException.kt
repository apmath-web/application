package com.apmath.applications.application.v1.exceptions

import io.ktor.http.HttpStatusCode

class NotFoundException(message: String) : ApiException(HttpStatusCode.NotFound,message)
