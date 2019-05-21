package com.apmath.applications.application.v1.actions.models

import com.apmath.applications.domain.data.Money

class LoanRequest {
    var amount: Money? = null
    var currency: String? = null
    var term: Int? = null
}