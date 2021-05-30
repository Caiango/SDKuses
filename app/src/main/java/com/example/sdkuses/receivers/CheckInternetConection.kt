package com.example.sdkuses.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.widget.Toast

class CheckInternetConection : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action

        when (action) {
            WifiManager.WIFI_STATE_CHANGED_ACTION -> {
                val state =
                    intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN)
                if (state == WifiManager.WIFI_STATE_ENABLED) {
                    if (checkConnection(context)) {
                        Toast.makeText(context, "Conectado", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Wifi ligado, porem necessita conectar na Internet", Toast.LENGTH_LONG).show()
                    }
                } else if (state == WifiManager.WIFI_STATE_DISABLED) {
                    Toast.makeText(context, "Desconectado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun checkConnection(context: Context): Boolean {
        val cm: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val isConnected = cm.isDefaultNetworkActive
        return isConnected
    }
}