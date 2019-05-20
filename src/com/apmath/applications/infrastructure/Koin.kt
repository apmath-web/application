package com.apmath.applications.com.apmath.applications.infrastructure

import com.apmath.applications.domain.ExpensesFetcherInterface
import com.apmath.applications.infrastructure.ExpensesFetcher
import io.ktor.config.ApplicationConfig
import org.koin.dsl.module

val applications = module {
    single {
        val config = getProperty<ApplicationConfig>("config")
        ExpensesFetcher(
            config.property("expenses.host").getString(),
            config.property("expenses.port").getString().toInt()
        ) as ExpensesFetcherInterface
    }
}
