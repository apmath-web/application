package com.apmath.applications.application.v1.actions.models

import com.apmath.applications.domain.models.applications.ApplicationCreationData as ApplicationDomain
import com.apmath.applications.domain.data.Currency
import com.apmath.applications.domain.data.Money
import com.apmath.applications.domain.data.Status

class Application {

    var clientId: String? = null
    var amount: Money? = null
    var currency: Currency? = null
    var coBorrowers: Array<Int>? = null
    var guarantors: Array<Int>? = null
    var term: Int? = null

}

fun Application.toApplicationDomain() = ApplicationDomain(

    clientId = clientId!!.toInt(),
    amount = amount!!,
    currency = currency!!,
    coBorrowers = coBorrowers!!,
    guarantors = guarantors!!,
    term = term!!

)
