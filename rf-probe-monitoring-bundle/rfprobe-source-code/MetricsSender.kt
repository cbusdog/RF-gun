
package com.datadog.rfprobe

import okhttp3.*
import com.google.gson.Gson
import java.io.IOException

object MetricsSender {
    fun send(metrics: List<Metric>, apiKey: String) {
        val json = Gson().toJson(mapOf("series" to metrics))
        val client = OkHttpClient()
        val body = RequestBody.create(MediaType.get("application/json"), json)
        val request = Request.Builder()
            .url("https://api.datadoghq.com/api/v2/series")
            .addHeader("DD-API-KEY", apiKey)
            .post(body)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {}
        })
    }
}
