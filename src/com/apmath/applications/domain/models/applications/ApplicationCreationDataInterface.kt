package com.apmath.applications.domain.models.applications

import com.apmath.applications.domain.data.Currency
import com.apmath.applications.domain.data.Money

interface ApplicationCreationDataInterface {

    val clientId: Int
    val amount: Money
    val currency: Currency
    val coBorrowers: Array<Int>
    val guarantors: Array<Int>
    val term: Int


}


