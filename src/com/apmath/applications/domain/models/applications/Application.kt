package com.apmath.applications.domain.models.applications

import com.apmath.applications.domain.data.Currency
import com.apmath.applications.domain.data.Money
import com.apmath.applications.domain.data.Status
import com.apmath.applications.domain.exceptions.runtime.ChangeIdentifiedApplicationIdException


class Application(

    override val clientId: Int,

    override val amount: Money,
    override val currency: Currency,
    override val coBorrowers: Array<Int>,
    override var guarantors: Array<Int>,
    override val term: Int,

    override val interest: Int,
    override val maxPayment: Money,

    override val maxAllowedAmount: Money,
    override val minTermForMaxAmount: Int,
    override val minTermForRequestedAmount: Int,
    override val requestedAmount: Money,
    override val status: Status

) : ApplicationInterface {

    override var id: Int? = null
        set(value) {
            if (field == null) {
                field = value
            } else {
                throw ChangeIdentifiedApplicationIdException()
            }
        }

    override var completed: Boolean = false
        private set
}
