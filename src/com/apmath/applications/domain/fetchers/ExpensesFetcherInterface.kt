package com.apmath.applications.domain.fetchers

import com.apmath.applications.domain.models.applications.ApplicationDetailsInterface
import com.apmath.applications.domain.models.applications.ApplicationInitializationInterface


interface ExpensesFetcherInterface {
    suspend fun initialization(application: ApplicationInitializationInterface): ApplicationDetailsInterface
}
