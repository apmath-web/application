package com.apmath.applications.infrastructure

import com.apmath.applications.domain.data.Money
import com.apmath.applications.domain.data.Status
import com.apmath.applications.domain.models.applications.Application
import com.apmath.applications.domain.models.applications.ApplicationDetailsInterface

class ApplicationDetails(

    override val maxAllowedAmount: Money,
    override val minTermForMaxAmount: Money,
    override val minTermForRequestedAmount: Money,
    override val requestedAmount: Money,
    override val status: Status
) : ApplicationDetailsInterface
