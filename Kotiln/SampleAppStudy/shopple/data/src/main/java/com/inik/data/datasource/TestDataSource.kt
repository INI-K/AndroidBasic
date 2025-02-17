package com.inik.data.datasource

import com.inik.data.model.TestModelResponse

class TestDataSource {
    fun getTestModelResponese() : TestModelResponse {
        return TestModelResponse("response")
    }
}