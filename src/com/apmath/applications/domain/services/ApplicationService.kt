package com.apmath.applications.domain.services

import com.apmath.applications.domain.data.Money
import com.apmath.applications.domain.exceptions.NoClientException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import com.apmath.applications.domain.data.Status
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
            clientsFetcher.isExists(clientId)
        }

        val interestsResult = GlobalScope.async {
            val applicationInit = application.toApplicationInitialization()
            interestsFetcher.initialization(applicationInit)
        }

        val expensesResult = GlobalScope.async {
            val applicationInit = application.toApplicationInitialization()
            expensesFetcher.initialization(applicationInit)
        }

        val client = clientsResult.await()
        val interest = interestsResult.await()
        val expense = expensesResult.await()

        if (client) {

            fun calcucatePayment(interest: Int, term: Int, amount: Money): Money {
                val interestMonth = interest.toDouble() / 12 / 100


                val payment = (interestMonth * Math.pow(
                    (1 + interestMonth),
                    term.toDouble()
                )) / (Math.pow(
                    (1 + interestMonth),
                    term.toDouble()
                ) - 1) * amount

                return payment.toLong()
            }

            fun calcucateTerm(interest: Int, payment: Money, amount: Money): Int {
                val interestMonth = interest.toDouble() / 100 / 12

                val interestPowTerm = amount * interestMonth / (amount - interestMonth * interestMonth * payment)
                return (Math.log(interestPowTerm) / Math.log(interestMonth)).toInt()
            }

            val interest = interest.interest
            val maxPayment = expense.maxPayment

            var maxAllowedAmount = application.amount * 2
            var minTermForMaxAmount = application.term
            var minTermForRequestedAmount = application.term
            var requestedAmount = application.amount
            var status = Status.APPROVED

            var calcucatedPayment = calcucatePayment(interest, application.term, application.amount)

            if (calcucatedPayment > maxPayment) {
                status = Status.REJECTED
            } else {
                status = Status.APPROVED

                calcucatedPayment = calcucatePayment(interest, application.term, maxAllowedAmount)
                if (calcucatedPayment < maxPayment) {
                    minTermForMaxAmount = calcucateTerm(interest, maxPayment, maxAllowedAmount)

                } else {
                    maxAllowedAmount = application.amount
                }

            }

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

        } else throw NoClientException()

    }

    override suspend fun get(applicationId: Int): ApplicationInterface {

        return repository.get(applicationId)
    }

}
