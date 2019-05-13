package com.apmath.applications.domain.exceptions

import io.ktor.http.HttpStatusCode

open class ApiException(

    override val message: String,
    val code: HttpStatusCode

) : Exception(message)
