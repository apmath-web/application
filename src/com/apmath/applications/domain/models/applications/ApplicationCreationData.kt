package com.apmath.applications.domain.models.applications

import com.apmath.applications.domain.data.Currency
import com.apmath.applications.domain.data.Money
import com.apmath.applications.infrastructure.ApplicationInitialization
import com.apmath.applications.infrastructure.applications

class ApplicationCreationData(

    override val clientId: Int,
    override val amount: Money,
    override val currency: Currency,
    override val coBorrowers: Array<Int>,
    override val guarantors: Array<Int>,
    override val term: Int

) : ApplicationCreationDataInterface

fun ApplicationCreationDataInterface.toApplication(
    interest: Int,
    maxPayment: Money,
    applicationDetails: ApplicationDetailsInterface
) = Application(

    clientId,
    amount,
    currency,
    coBorrowers,
    guarantors,
    term,

    applicationDetails.interest,
    applicationDetails.maxPayment,

    applicationDetails.maxAllowedAmount,
    applicationDetails.minTermForMaxAmount,
    applicationDetails.minTermForRequestedAmount,
    applicationDetails.requestedAmount,
    applicationDetails.status

)

fun ApplicationCreationDataInterface.toApplicationInitialization() = ApplicationInitialization(
    clientId = clientId,
    coBorrowers = coBorrowers
)
