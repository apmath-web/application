package com.apmath.applications.application.v1.models

import com.apmath.applications.domain.models.applications.Application as ApplicationDomain
import com.apmath.applications.domain.data.Currency
import com.apmath.applications.domain.data.Money
import com.apmath.applications.domain.data.Status

class Application {

    var clientId: Int? = null
    var amount: Money? = null
    var currency: Currency? = null
    var coBorrowers: Array<Int>? = null
    var guarantors: Array<Int>? = null
    var term: Int? = null
    var interest: Int? = null
    var maxAllowedAmount: Money? = null
    var minTermForMaxAmount: Money? = null
    var minTermForRequestedAmount: Money? = null
    var requestedAmount: Money? = null
    var status: Status? = null


}

fun Application.toApplicationClient() = ApplicationDomain(

    clientId = clientId!!,
    amount = amount!!,
    currency = currency!!,
    coBorrowers = coBorrowers!!,
    guarantors = guarantors!!,
    term=term!!,
    interest=interest!!,
    maxAllowedAmount=maxAllowedAmount!!,
    minTermForMaxAmount=minTermForMaxAmount!!,
    minTermForRequestedAmount=minTermForRequestedAmount!!,
    requestedAmount=requestedAmount!!,
    status=status!!

)
