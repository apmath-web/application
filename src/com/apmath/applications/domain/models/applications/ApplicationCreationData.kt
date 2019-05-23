package com.apmath.applications.domain.models.applications

import com.apmath.applications.domain.data.Currency
import com.apmath.applications.domain.data.Money

class ApplicationCreationData(

    override val clientId: Int,
    override val amount: Money,
    override val currency: Currency,
    override val coBorrowers: Array<Int>,
    override val guarantors: Array<Int>

) :ApplicationCreationDataInterface
