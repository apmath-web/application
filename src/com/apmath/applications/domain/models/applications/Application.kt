package com.apmath.applications.domain.models.applications

import com.apmath.applications.domain.data.Currency
import com.apmath.applications.domain.data.Money
import com.apmath.applications.domain.data.Status
import com.apmath.applications.domain.exceptions.runtime.ChangeIdentifiedApplicationIdException


data class Application(

    override val amount: Money,
    override val currency: Currency,
    override val period: Int,
    override val clientId: Int,
    override val interest: Int,
    override val maxAmount: Money,
    override val minAmount: Money,

    override val loan: Int,
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
