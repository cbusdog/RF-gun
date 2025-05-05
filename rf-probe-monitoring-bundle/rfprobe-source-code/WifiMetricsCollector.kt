
package com.datadog.rfprobe

import android.content.Context
import android.net.wifi.WifiManager

fun collectWifiInfo(context: Context): List<Metric> {
    val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    val info = wifiManager.connectionInfo
    return listOf(
        Metric("rfgun.wifi.signal_strength", info.rssi.toDouble()),
        Metric("rfgun.wifi.ssid", info.ssid),
        Metric("rfgun.wifi.bssid", info.bssid)
    )
}
