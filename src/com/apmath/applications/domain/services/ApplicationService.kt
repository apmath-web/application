package com.apmath.applications.domain.services

import com.apmath.application.domain.exceptions.NoClientException
import com.apmath.applications.domain.models.applications.Application
import com.apmath.applications.domain.models.applications.ApplicationCreationData
import com.apmath.applications.domain.models.applications.ApplicationCreationDataInterface
import com.apmath.applications.domain.models.applications.toApplication
import com.apmath.applications.domain.models.applications.ApplicationInterface
import com.apmath.applications.domain.repositories.RepositoryInterface
import com.apmath.applications.infrastructure.applications
import com.apmath.applications.infrastructure.fetchers.ClientsFetcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class ApplicationService(
    private val clientsFetcher: ClientsFetcher,
    private val repository: RepositoryInterface
) : ApplicationServiceInterface {
    override suspend fun add(application: ApplicationCreationDataInterface): Int {
        val clientId = application.clientId

        val clientResult = GlobalScope.async {
            clientsFetcher.isExists(clientId)
        }

        val client = clientResult.await()

        when {
            //client does not exists
            !client
            -> throw NoClientException()

            else -> {

                val applicationEmployee = application.toApplication()

                repository.store(applicationEmployee)
                return applicationEmployee.id!!
            }

        }
    }

    override suspend fun get(isService: Boolean, clientIdHeader: Int?, clientId: Int?): List<ApplicationInterface> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
