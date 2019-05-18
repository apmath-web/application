package com.apmath.applications.application.v1.actions

import com.apmath.applications.application.v1.actions.models.LoanRequest
import com.apmath.applications.application.v1.actions.models.LoanResponseApproved
import com.apmath.applications.application.v1.actions.models.LoanResponseDeclined
import io.ktor.application.ApplicationCall
import io.ktor.features.BadRequestException
import io.ktor.request.receive
import io.ktor.response.respond

suspend fun ApplicationCall.v1LoanRequest(client: String, application: String) {

    val loanRequest = receive<LoanRequest>()
    // TODO validate loanRequest, client and application
    // map client -> clientId application -> applicationId

    // hardcoded logic
    val clientId: Int
    try {
        clientId = client.toInt()
    } catch (e: NumberFormatException) {
        throw BadRequestException("Wrong clientId")
    }

    when {
        clientId % 2 == 0 -> respond(LoanResponseApproved(11, loanRequest.term ?: 24))
        else -> respond(LoanResponseDeclined())
    }
}
