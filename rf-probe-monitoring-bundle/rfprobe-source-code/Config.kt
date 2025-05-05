
package com.datadog.rfprobe

import android.content.Context
import android.os.Environment
import com.google.gson.Gson
import java.io.File

data class Config(
    val apiKey: String,
    val deviceId: String,
    val warehouseLocation: String,
    val intervalSeconds: Int
) {
    companion object {
        fun load(context: Context): Config {
            val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "config.json")
            val json = file.readText()
            return Gson().fromJson(json, Config::class.java)
        }
    }
}
