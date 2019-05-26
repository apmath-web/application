package com.apmath.applications.domain.services

import com.apmath.application.domain.exceptions.NoClientException
import com.apmath.applications.domain.data.Status
import com.apmath.applications.domain.models.applications.*
import com.apmath.applications.domain.repositories.RepositoryInterface
import com.apmath.applications.infrastructure.ApplicationDetails
import com.apmath.applications.infrastructure.applications
import com.apmath.applications.infrastructure.fetchers.ClientsFetcher
import com.apmath.applications.infrastructure.fetchers.ExpensesFetcher
import com.apmath.applications.infrastructure.fetchers.InterestsFetcher
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class ApplicationService(
    private val clientsFetcher: ClientsFetcher,
    private val interestsFetcher: InterestsFetcher,
    private val expensesFetcher: ExpensesFetcher,
    private val repository: RepositoryInterface
) : ApplicationServiceInterface {
    override suspend fun add(application: ApplicationCreationDataInterface): Int {
        val clientId = application.clientId

        val clientResult = GlobalScope.async {
            clientsFetcher.isExists(clientId)
        }

        val interestsResult = GlobalScope.async(start = CoroutineStart.LAZY) {
            val applicationInit = application.toApplicationInitialization()
            interestsFetcher.initialization(applicationInit)
        }

        val expensesResult = GlobalScope.async(start = CoroutineStart.LAZY) {
            val applicationInit = application.toApplicationInitialization()
            expensesFetcher.initialization(applicationInit)
        }

        val client = clientResult.await()
        val applicationDetails = expensesResult.await()

        when {
            //client does not exists
            !client
            -> throw NoClientException()

            else -> {

                var term = 12
                val interests = 12
                val expenses = expensesResult.await()
                val maxAllowedAmount = expenses.maxAllowedAmount.toInt()
                var minTermForMaxAmount = 1
                var minTermForRequestedAmount = 1
                val requestedAmount = application.amount
                val status = Status.APPROVED

                if (clientId % 2 == 0) {
                    status != Status.APPROVED
                } else {
                    status != Status.REJECTED
                }

                minTermForMaxAmount = maxAllowedAmount / 200
                minTermForRequestedAmount = requestedAmount.toInt() / 200
                if (requestedAmount < maxAllowedAmount) {
                    term = minTermForRequestedAmount*(1+interests)
                } else {
                    term = minTermForMaxAmount*(1+interests)
                }


                val applicationEmployee = application.toApplication(applicationDetails)

                repository.store(applicationEmployee)
                return applicationEmployee.id!!
            }

        }
    }

    override suspend fun get(isService: Boolean, clientIdHeader: Int?, clientId: Int?): List<ApplicationInterface> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
