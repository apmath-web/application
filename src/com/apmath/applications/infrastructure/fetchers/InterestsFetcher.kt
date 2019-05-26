package com.apmath.applications.infrastructure.fetchers

import com.apmath.applications.application.v1.actions.models.Application
import com.apmath.applications.domain.fetchers.ClientsFetcherInterface
import com.apmath.applications.domain.fetchers.InterestsFetcherInterface
import com.apmath.applications.domain.models.applications.ApplicationInitializationInterface
import com.apmath.applications.infrastructure.ApplicationDetails
import io.ktor.client.response.HttpResponse
import io.ktor.http.isSuccess

class InterestsFetcher(
    host: String,
    port: Int
) : AbstractFetcher(host, port), InterestsFetcherInterface {
    override suspend fun initialization(application: ApplicationInitializationInterface): ApplicationDetails {
        return post("/v1/application", application)
    }
}
