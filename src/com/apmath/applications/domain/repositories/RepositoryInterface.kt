package com.apmath.applications.domain.repositories

import com.apmath.applications.domain.models.applications.Application
import com.apmath.applications.domain.models.applications.ApplicationInterface

interface RepositoryInterface {
    fun get(id: Int): ApplicationInterface
    fun getAll(): List<ApplicationInterface>
    fun store(loan: ApplicationInterface)
    fun remove(loan: ApplicationInterface)
}
