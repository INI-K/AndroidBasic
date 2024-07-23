package com.inik.kaybucks

import android.content.Context
import com.google.gson.Gson
import java.io.IOException
import java.nio.ByteBuffer

fun Context.readData(): Home? {

    return try {
        val inputStream = this.resources.assets.open("home.json")
        val buffer = ByteArray(inputStream.available() ?: 0)
        inputStream.read(buffer)
        inputStream.close()

        val gson = Gson()
        gson.fromJson(String(buffer), Home::class.java)
    } catch (e: IOException) {
        return null
    }
}
