package com.apmath.applications.domain.services

import com.apmath.applications.domain.models.applications.ApplicationCreationData
import com.apmath.applications.domain.models.applications.ApplicationCreationDataInterface
import com.apmath.applications.domain.models.applications.ApplicationInterface

class ApplicationService() : ApplicationServiceInterface{
    override suspend fun add(application: ApplicationCreationDataInterface): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
