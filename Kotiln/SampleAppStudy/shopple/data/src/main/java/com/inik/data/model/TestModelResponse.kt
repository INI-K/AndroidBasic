package com.inik.data.model

import com.inik.domain.model.TestModel

class TestModelResponse(val name: String?)

fun TestModelResponse.toDomainModel(): TestModel?{
    if(name != null){
        return TestModel(name)
    }
    return null
}