package com.apmath.applications.application.v1.actions.models

class LoanResponseApproved(interest: Int, term: Int) {
    val status: String = "approved"
    var interest: Int = interest
    val term: Int = term
}