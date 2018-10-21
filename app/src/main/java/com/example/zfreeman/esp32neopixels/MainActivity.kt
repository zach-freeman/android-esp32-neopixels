package com.example.zfreeman.esp32neopixels

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity() {
    private var isLightOn = false
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            sendLightMessage()
        }
    }

    private fun sendLightMessage()
    {
        if (!isLightOn)
        {
            doAsync{
                val result = URL("http://espressif/lightOn").readText()
                uiThread {
                    Log.d("Request", result)
                    longToast("Request performed")
                }
            }
            isLightOn = true
            button.setText("On")
        } else {
            doAsync{
                val result = URL("http://esprissif/lightOff").readText()
                uiThread {
                    Log.d("Request", result)
                    longToast("Request performed")
                }
            }
            isLightOn = false
            button.setText("Off")
        }

    }
}
