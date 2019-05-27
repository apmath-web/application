package com.apmath.applications.domain.models.applications

import com.apmath.applications.domain.data.Money
import com.apmath.applications.domain.data.Status

interface ApplicationDetailsInterface {
    val interest: Int
    val maxAllowedAmount: Money
    val minTermForMaxAmount: Money
    val minTermForRequestedAmount: Money
    val requestedAmount: Money
    val status: Status
}
