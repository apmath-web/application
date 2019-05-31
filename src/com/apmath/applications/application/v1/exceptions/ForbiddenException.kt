package com.apmath.applications.application.v1.exceptions

import io.ktor.http.HttpStatusCode

class ForbiddenException(message: String) : ApiException(HttpStatusCode.Forbidden, message)
