package com.apmath.applications.infrastructure.fetchers

import com.apmath.applications.domain.ExpensesFetcherInterface
import com.apmath.applications.domain.fetchers.InterestsFetcherInterface
import com.apmath.applications.domain.models.applications.ApplicationInitializationInterface
import com.apmath.applications.infrastructure.ApplicationDetails

class ExpensesFetcher(
    host: String,
    port: Int
) : AbstractFetcher(host, port), InterestsFetcherInterface {
    override suspend fun initialization(application: ApplicationInitializationInterface): ApplicationDetails {
        return post("/v1/application", application)
    }
}