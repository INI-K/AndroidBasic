package com.inik.domain.repository

import com.inik.domain.model.TestModel

interface TestRepository {
    fun getTestData() : TestModel
}