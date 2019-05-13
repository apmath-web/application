package com.apmath.applications.domain.models.applications

import com.apmath.applications.domain.data.Currency
import com.apmath.applications.domain.data.Money
import com.apmath.applications.domain.data.Status

interface ApplicationInterface{

    var id: Int?

    val amount: Money
    val currency: Currency
    val period: Int
    val clientId: Int
    val interest: Int
    val maxAmount: Money
    val minAmount: Money

    val loan: Int
    val status: Status
    val coBorrowers: Array<Int>
    var guarantors: Array<Int>

}
