package com.apmath.applications.infrastructure

import com.apmath.applications.domain.data.Money
import com.apmath.applications.domain.data.Status
import com.apmath.applications.domain.fetchers.InterestsFetcherInterface
import com.apmath.applications.domain.models.applications.Application
import com.apmath.applications.domain.models.applications.ApplicationDetailsInterface

class ApplicationDetails(

    override val interest: Int,
    override val maxPayment: Money,

    override val maxAllowedAmount: Money,
    override val minTermForMaxAmount: Int,
    override val minTermForRequestedAmount: Int,
    override val requestedAmount: Money,
    override val status: Status
) : ApplicationDetailsInterface
