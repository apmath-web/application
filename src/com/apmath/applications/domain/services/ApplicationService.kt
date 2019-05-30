package com.apmath.applications.domain.services

import com.apmath.applications.domain.exceptions.NoClientException
import com.apmath.applications.domain.repositories.Repository
import com.apmath.applications.infrastructure.fetchers.ClientsFetcher
import com.apmath.applications.infrastructure.fetchers.ExpensesFetcher
import com.apmath.applications.infrastructure.fetchers.InterestsFetcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import com.apmath.applications.domain.data.Status
import com.apmath.applications.domain.models.applications.*
import com.apmath.applications.infrastructure.ApplicationDetails

class ApplicationService(
    private val clientsFetcher: ClientsFetcher,
    private val interestsFetcher: InterestsFetcher,
    private val expensesFetcher: ExpensesFetcher,
    private val repository: Repository
) : ApplicationServiceInterface {
    override suspend fun add(application: ApplicationCreationDataInterface): Int {
        val clientId = application.clientId

        val clientsResult = GlobalScope.async {
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

        when {
            !client
            -> throw NoClientException()

            else -> {
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

            }
        }
    }

}
