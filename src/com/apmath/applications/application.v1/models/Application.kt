package com.apmath.applications.application.v1.models

import com.apmath.applications.domain.models.applications.Application as ApplicationDomain
import com.apmath.applications.domain.data.Currency
import com.apmath.applications.domain.data.Money
import com.apmath.applications.domain.data.Status

class Application {

    var clientId: Int? = null
    var amount: Money? = null
    var currency: Currency? = null
    var loan: Int? = null
    var coBorrowers: Array<Int>? = null
    var guarantors: Array<Int>? = null
    var period: Int? = null
    var interest: Int? = null
    var maxAmount: Money? = null
    var minAmount: Money? = null
    var status: Status? = null


}

fun Application.toApplicationClient() = ApplicationDomain(

    clientId = clientId!!,
    amount = amount!!,
    currency = currency!!,
    loan = loan!!,
    coBorrowers = coBorrowers!!,
    guarantors = guarantors!!,
    period=period!!,
    interest=interest!!,
    maxAmount=maxAmount!!,
    minAmount=minAmount!!,
    status=status!!

)
