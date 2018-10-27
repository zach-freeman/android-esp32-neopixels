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
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        button.setOnClickListener {
            sendLightMessage()
        }
    }

    private fun sendLightMessage()
    {
            doAsync{
                val statusResult = URL("http://espressif/lightStatus").readText()
                uiThread {
                    Log.wtf("Request", statusResult)
                    longToast("Request performed")
                }
                if (statusResult == "On")
                {
                    val result = URL("http://espressif/lightOff").readText()
                    uiThread {
                        Log.wtf("Request", result)
                        longToast("Request performed")
                        button.setText("Off")
                    }

                }
                else
                {
                    val result = URL("http://espressif/lightOn").readText()
                    uiThread {
                        Log.wtf("Request", result)
                        longToast("Request performed")
                        button.setText("On")
                    }
                }
            }

    }
}
