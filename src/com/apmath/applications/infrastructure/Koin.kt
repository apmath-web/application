package com.apmath.applications.infrastructure

import org.koin.dsl.module
import com.apmath.applications.domain.ExpensesFetcherInterface
import com.apmath.applications.domain.services.ApplicationService
import com.apmath.applications.domain.services.ApplicationServiceInterface
import com.apmath.applications.infrastructure.fetchers.ExpensesFetcher
import io.ktor.config.ApplicationConfig
import org.koin.experimental.builder.singleBy

val applications = module {

    singleBy<ApplicationServiceInterface, ApplicationService>()

    single {
        val config = getProperty<ApplicationConfig>("config")
        ExpensesFetcher(
            config.property("expenses.host").getString(),
            config.property("expenses.port").getString().toInt()
        ) as ExpensesFetcherInterface
    }

}
