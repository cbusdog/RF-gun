package com.datadog.rfprobe

import android.content.Context
import android.net.wifi.WifiManager

fun collectWifiInfo(context: Context, config: Config): List<Metric> {
    val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    val info = wifiManager.connectionInfo
    val timestamp = System.currentTimeMillis() / 1000

    return listOf(
        Metric("rfgun.wifi.signal_strength", "gauge", listOf(listOf(timestamp, info.rssi.toDouble())),
            listOf("device_id:${config.deviceId}", "warehouse_location:${config.warehouseLocation}")),
        Metric("rfgun.wifi.ssid", "gauge", listOf(listOf(timestamp, info.ssid.hashCode().toDouble())),
            listOf("device_id:${config.deviceId}", "warehouse_location:${config.warehouseLocation}")),
        Metric("rfgun.wifi.bssid", "gauge", listOf(listOf(timestamp, info.bssid.hashCode().toDouble())),
            listOf("device_id:${config.deviceId}", "warehouse_location:${config.warehouseLocation}"))
    )
}
