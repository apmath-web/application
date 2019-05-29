package com.apmath.applications.domain.fetchers

interface ClientsFetcherInterface {
    suspend fun isExists(clientId: Int): Boolean
}
