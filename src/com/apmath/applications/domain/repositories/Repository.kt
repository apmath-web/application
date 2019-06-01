package com.apmath.applications.domain.repositories

import com.apmath.applications.domain.exceptions.ApplicationNotFoundException
import com.apmath.applications.domain.exceptions.RemoveUnfinishedApplicationException
import com.apmath.applications.domain.exceptions.RemoveUnidentifiedApplicationException
import com.apmath.applications.domain.exceptions.runtime.RemoveAbsentApplicationException
import com.apmath.applications.domain.exceptions.runtime.StoreIdentifiedApplicationException
import com.apmath.applications.domain.models.applications.ApplicationInterface

class Repository : RepositoryInterface {
    private var identity: Int = 1
    private val applications: HashMap<Int, ApplicationInterface> = hashMapOf()

    override fun getAll(): List<ApplicationInterface> = applications.toList().map { it.second }

    override fun get(id: Int): ApplicationInterface {
        return applications[id] ?: throw ApplicationNotFoundException()
    }

    override fun store(application: ApplicationInterface) {
        if (application.id != null) {
            throw StoreIdentifiedApplicationException()
        }
        identity = application.id
        applications[identity++] = application
    }

    override fun remove(application: ApplicationInterface) {
        when {
            application.id == null -> throw RemoveUnidentifiedApplicationException()
            !application.completed -> throw RemoveUnfinishedApplicationException()
            !this.applications.containsKey(application.id as Int) -> throw RemoveAbsentApplicationException()
        }

        this.applications.remove(application.id)
    }

}
