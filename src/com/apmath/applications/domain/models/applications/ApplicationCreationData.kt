package com.apmath.applications.domain.models.applications

import com.apmath.applications.domain.data.Currency
import com.apmath.applications.domain.data.Money
import com.apmath.applications.infrastructure.applications

data class ApplicationCreationData(

    override val clientId: Int,
    override val amount: Money,
    override val currency: Currency,
    override val coBorrowers: Array<Int>,
    override val guarantors: Array<Int>

) : ApplicationCreationDataInterface

//fun ApplicationCreationDataInterface.toApplication(applicationDetails: ApplicationDetailsInterface) = Application(
fun ApplicationCreationDataInterface.toApplication() = Application(

    clientId,
    amount,
    currency,
    coBorrowers,
    guarantors

//    applicationDetails.term,
//    applicationDetails.interest,
//    applicationDetails.maxAllowedAmount,
//    applicationDetails.minTermForMaxAmount,
//    applicationDetails.minTermForRequestedAmount,
//    applicationDetails.requestedAmount,
//    applicationDetails.status


)
