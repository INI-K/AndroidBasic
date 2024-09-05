package com.inik.kaybucks

import android.content.Context
import com.google.gson.Gson
import java.io.IOException
import java.nio.ByteBuffer

fun <T> Context.readData(fileName: String, classT: Class<T>): T? {

    return try {
        val inputStream = this.resources.assets.open(fileName)
        val buffer = ByteArray(inputStream.available() ?: 0)
        inputStream.read(buffer)
        inputStream.close()

        val gson = Gson()
        gson.fromJson(String(buffer), classT)
    } catch (e: IOException) {
        return null
    }
}
