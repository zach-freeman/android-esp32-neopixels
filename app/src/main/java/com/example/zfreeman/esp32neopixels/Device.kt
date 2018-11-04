package com.example.zfreeman.esp32neopixels


import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Device(photoJSON: JSONObject) : Serializable {

    private lateinit var photoDate: String
    lateinit var explanation: String
        private set
    lateinit var url: String
        private set

    init {
        try {
            photoDate = photoJSON.getString(PHOTO_DATE)
            explanation = photoJSON.getString(PHOTO_EXPLANATION)
            url = photoJSON.getString(PHOTO_URL)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    companion object {
        private val PHOTO_DATE = "date"
        private val PHOTO_EXPLANATION = "explanation"
        private val PHOTO_URL = "url"
    }
}