package com.apmath.applications.domain.models.applications

import com.apmath.applications.domain.data.Currency
import com.apmath.applications.domain.data.Money
import com.apmath.applications.domain.data.Status
import com.apmath.applications.domain.exceptions.runtime.ChangeIdentifiedApplicationIdException


class Application(

    override val amount: Money,
    override val currency: Currency,
    override val term: Int,
    override val clientId: Int,
    override val interest: Int,
    override val maxAllowedAmount: Money,
    override val minTermForMaxAmount: Money,
    override val minTermForRequestedAmount: Money,
    override val requestedAmount: Money,

    override val status: Status,
    override val coBorrowers: Array<Int>,
    override var guarantors: Array<Int>

) : ApplicationInterface{

    override var id: Int? = null
        set(value){
            if(field==null){
                field=value
            }else{
                throw ChangeIdentifiedApplicationIdException()
            }
        }
}
