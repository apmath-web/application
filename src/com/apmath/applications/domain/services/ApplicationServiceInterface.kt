package com.apmath.applications.domain.services

import com.apmath.applications.domain.models.applications.ApplicationCreationDataInterface
import com.apmath.applications.domain.models.applications.ApplicationInterface

interface ApplicationServiceInterface {

    suspend fun add(application: ApplicationCreationDataInterface): Int
    suspend fun get(applicationId: Int): ApplicationInterface
}
