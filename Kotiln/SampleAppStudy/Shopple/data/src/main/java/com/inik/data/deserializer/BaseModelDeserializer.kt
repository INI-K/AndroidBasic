package com.inik.data.deserializer

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.inik.domain.model.Banner
import com.inik.domain.model.BaseModel
import com.inik.domain.model.ModelType
import com.inik.domain.model.Product
import java.lang.reflect.Type

class BaseModelDeserializer : JsonDeserializer<BaseModel> {
    companion object {
        private const val TYPE = "type"
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): BaseModel {
        val root = json?.asJsonObject
        val gson = GsonBuilder().create()

        val typeString = root?.get(TYPE)?.asString ?: ""

        return when(ModelType.valueOf(typeString)){
            ModelType.PRODUCT -> {
                gson.fromJson(root,Product::class.java)
            }
            ModelType.BANNER -> {
                gson.fromJson(root,Banner::class.java)
            }
        }
    }
}