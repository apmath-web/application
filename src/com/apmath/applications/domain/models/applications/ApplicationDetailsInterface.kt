package com.apmath.applications.domain.models.applications

import com.apmath.applications.domain.data.Money
import com.apmath.applications.domain.data.Status

interface ApplicationDetailsInterface {
    val interest: Int
    val maxPayment: Money

    val maxAllowedAmount: Money
    val minTermForMaxAmount: Int
    val minTermForRequestedAmount: Int
    val requestedAmount: Money
    val status: Status
}
