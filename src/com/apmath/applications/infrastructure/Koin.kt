package com.apmath.applications.com.apmath.applications.infrastructure

import com.apmath.applications.domain.services.ApplicationService
import com.apmath.applications.domain.services.ApplicationServiceInterface
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy

val applications = module {
    singleBy<ApplicationServiceInterface, ApplicationService>()
}
