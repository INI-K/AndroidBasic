package com.inik.data.repository

import com.inik.data.datasource.TestDataSource
import com.inik.data.model.toDomainModel
import com.inik.domain.model.TestModel
import com.inik.domain.repository.TestRepository

class TestRepositoryImpl(val dataSource: TestDataSource): TestRepository {
    override fun getTestData(): TestModel {
        return dataSource.getTestModelResponese().toDomainModel()
    }
}