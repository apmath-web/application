package com.apmath.applications.infrastructure

import com.apmath.applications.domain.models.applications.ApplicationInitializationInterface

class ApplicationInitialization(
    override val clientId: Int,
    override val coBorrowers: Array<Int>
) : ApplicationInitializationInterface
