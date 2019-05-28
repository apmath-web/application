package com.apmath.applications.domain.repositories

import com.apmath.applications.domain.models.applications.ApplicationInterface

interface RepositoryInterface {
    fun get(id: Int): ApplicationInterface
    fun getAll(): List<ApplicationInterface>
    fun store(application: ApplicationInterface)
    fun remove(application: ApplicationInterface)
}
