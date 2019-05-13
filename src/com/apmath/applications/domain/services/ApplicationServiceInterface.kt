package com.apmath.applications.domain.services

import com.apmath.applications.application.v1.models.Application
import com.apmath.applications.domain.models.applications.ApplicationInterface

interface ApplicationServiceInterface{

    suspend fun add(application: ApplicationInterface): Int

}
