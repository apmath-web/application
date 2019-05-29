package com.apmath.applications.infrastructure.fetchers

import com.apmath.applications.domain.fetchers.ClientsFetcherInterface
import io.ktor.client.response.HttpResponse
import io.ktor.http.isSuccess

class ClientsFetcher(
    host: String,
    port: Int
) : AbstractFetcher(host, port), ClientsFetcherInterface {

    suspend fun getClient(clientId: Int): HttpResponse {
        return get("/v1/$clientId")
    }

    override suspend fun isExists(clientId: Int): Boolean {
        return getClient(clientId).status.isSuccess()
    }
}
