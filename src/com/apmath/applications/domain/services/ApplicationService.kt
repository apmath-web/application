package com.apmath.applications.domain.services

import com.apmath.applications.domain.exceptions.NoClientException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import com.apmath.applications.domain.data.Status
import com.apmath.applications.domain.exceptions.ForbiddenAccessException
import com.apmath.applications.domain.fetchers.ClientsFetcherInterface
import com.apmath.applications.domain.fetchers.ExpensesFetcherInterface
import com.apmath.applications.domain.fetchers.InterestsFetcherInterface
import com.apmath.applications.domain.models.applications.*
import com.apmath.applications.domain.repositories.RepositoryInterface
import com.apmath.applications.infrastructure.ApplicationDetails

class ApplicationService(
    private val clientsFetcher: ClientsFetcherInterface,
    private val interestsFetcher: InterestsFetcherInterface,
    private val expensesFetcher: ExpensesFetcherInterface,
    private val repository: RepositoryInterface
) : ApplicationServiceInterface {
    override suspend fun add(application: ApplicationCreationDataInterface): Int {
        val clientId = application.clientId

        val clientsResult = GlobalScope.async {
            //TODO: Find another solutions
            clientsFetcher.isExists(clientId)
        }

        val interestsResult = GlobalScope.async {
            val applicationInit = application.toApplicationInitialization()
            interestsFetcher.initialization(applicationInit)
        }

        val expensessResult = GlobalScope.async {
            val applicationInit = application.toApplicationInitialization()
            expensesFetcher.initialization(applicationInit)
        }

        val client = clientsResult.await()
        val interest = interestsResult.await()
        val expense = expensessResult.await()

        if (client) {

            val interest = interest.interest
            val maxPayment = expense.maxPayment

            var maxAllowedAmount = application.amount
            var minTermForMaxAmount = 1
            var minTermForRequestedAmount = application.term
            var requestedAmount = application.amount
            var status = Status.APPROVED

            val applicationDetails = ApplicationDetails(
                interest,
                maxPayment,
                maxAllowedAmount,
                minTermForMaxAmount,
                minTermForRequestedAmount,
                requestedAmount,
                status
            )

            val applicationEmployee = application.toApplication(applicationDetails)

            repository.store(applicationEmployee)
            return applicationEmployee.id!!

        } else {
            throw NoClientException()
        }

    }

    override suspend fun get(applicationId: Int): ApplicationInterface {

        return repository.get(applicationId)
    }

}
