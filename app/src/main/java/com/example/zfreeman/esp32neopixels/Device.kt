package com.example.zfreeman.esp32neopixels


import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Device(deviceJSON: JSONObject) : Serializable {

    lateinit var deviceName: String
    lateinit var deviceHostname: String
        private set

    init {
        try {
            deviceName = deviceJSON.getString(DEVICE_NAME)
            deviceHostname = deviceJSON.getString(DEVICE_HOSTNAME)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    companion object {
        val DEVICE_NAME = "name"
        val DEVICE_HOSTNAME = "hostname"
    }
}