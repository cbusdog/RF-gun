
package com.datadog.rfprobe

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.util.*

class MainService : Service() {
    private lateinit var timer: Timer

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val config = Config.load(applicationContext)
        timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val metrics = collectAllMetrics(applicationContext, config)
                MetricsSender.send(metrics, config.apiKey)
            }
        }, 0, config.intervalSeconds * 1000L)
        return START_STICKY
    }

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
