package com.apmath.applications.infrastructure

import com.apmath.applications.domain.fetchers.ClientsFetcherInterface
import com.apmath.applications.domain.fetchers.ExpensesFetcherInterface
import com.apmath.applications.domain.fetchers.InterestsFetcherInterface
import com.apmath.applications.domain.repositories.Repository
import com.apmath.applications.domain.repositories.RepositoryInterface
import org.koin.dsl.module
import com.apmath.applications.domain.services.ApplicationService
import com.apmath.applications.domain.services.ApplicationServiceInterface
import com.apmath.applications.infrastructure.fetchers.ClientsFetcher
import com.apmath.applications.infrastructure.fetchers.ExpensesFetcher
import com.apmath.applications.infrastructure.fetchers.InterestsFetcher
import io.ktor.config.ApplicationConfig
import io.ktor.util.KtorExperimentalAPI
import org.koin.experimental.builder.singleBy
@KtorExperimentalAPI
val applications = module {

    singleBy<ApplicationServiceInterface, ApplicationService>()

    single {
        val config = getProperty<ApplicationConfig>("config")
        ClientsFetcher(
            config.property("clients.host").getString(),
            config.property("clients.port").getString().toInt()
        ) as ClientsFetcherInterface
    }
    single {
        val config = getProperty<ApplicationConfig>("config")
        ExpensesFetcher(
            config.property("expenses.host").getString(),
            config.property("expenses.port").getString().toInt()
        ) as ExpensesFetcherInterface
    }
    single {
        val config = getProperty<ApplicationConfig>("config")
        InterestsFetcher(
            config.property("interests.host").getString(),
            config.property("interests.port").getString().toInt()
        ) as InterestsFetcherInterface
    }
    singleBy<RepositoryInterface,Repository>()
}
