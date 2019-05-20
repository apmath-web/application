package com.apmath.applications.infrastructure

import com.apmath.applications.domain.ExpensesFetcherInterface

// TODO move to appropriate package if there is need
class ExpensesFetcher(host: String, port: Int): ExpensesFetcherInterface {
    private val host: String = host
    private val port: Int = port
}
