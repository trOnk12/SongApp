package com.example.tooploxsongapp.data.utils

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.lang.NumberFormatException

class IntTypeAdapter : TypeAdapter<Int>() {

    override fun read(reader: JsonReader): Int? {
        if (reader.peek() === JsonToken.NULL) {
            reader.nextNull()
            return null
        }

        return try {
            val stringValue = reader.nextString()
            val value = java.lang.Integer.valueOf(stringValue)
            return value
        } catch (e: NumberFormatException) {
            null
        }


    }

    override fun write(writer: JsonWriter, value: Int?) {
        if (value == null) {
            writer.nullValue()
            return
        }
        writer.value(value)
    }
}