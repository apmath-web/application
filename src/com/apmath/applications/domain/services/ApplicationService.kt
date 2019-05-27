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

                var status = Status.APPROVED
                val currency = application.currency
                var interest = 12
                val maxAllowedAmount = expensesResult.await().maxAllowedAmount.toLong()
                var minTermForMaxAmount = 1.toLong()
                var minTermForRequestedAmount = 1.toLong()
                val requestedAmount = application.amount


                if (clientId % 2 == 0) {
                    status = Status.APPROVED
                } else {
                    status = Status.REJECTED
                }

                if (currency == "RUB"){
                    interest = 5
                }else if (currency == "EUR"){
                    interest = 3
                }else if (currency == "USD"){
                    interest = 2
                }

                minTermForMaxAmount = (maxAllowedAmount*(1+interest))/application.term
                minTermForRequestedAmount = (application.amount*(1+interest))/application.term

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
