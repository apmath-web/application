package com.apmath.applications.domain.fetchers

import com.apmath.applications.domain.models.applications.Application
import com.apmath.applications.domain.models.applications.ApplicationDetailsInterface

interface ClientsFetcherInterface {
    suspend fun isExists(clientId: Int): Boolean
}
