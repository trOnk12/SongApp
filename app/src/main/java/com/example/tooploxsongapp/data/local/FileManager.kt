package com.example.tooploxsongapp.data.local

import android.content.Context
import java.io.IOException

class FileManager(private var context:Context) {

    fun readJSONFromFile(fileName:String): String? {
        val json: String?
        try {
            val  inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json =  String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

}