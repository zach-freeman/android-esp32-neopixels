package com.example.zfreeman.esp32neopixels

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import java.net.URL
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var lightSwitch: Switch
    companion object {
        private const val SERVER_NAME = "espressif"
        private const val LIGHT_STATUS_PATH = "lightStatus"
        private const val LIGHT_ON_PATH = "lightOn"
        private const val LIGHT_OFF_PATH = "lightOff"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lightSwitch = findViewById(R.id.lightSwitch)
        initializeSwitch()

        lightSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            sendLightMessage(isChecked)
        }
    }

    private fun initializeSwitch()
    {
        doAsync{
            val statusResult = URL("http://$SERVER_NAME/$LIGHT_STATUS_PATH").readText()

            if (statusResult == "On")
            {
                uiThread {
                    lightSwitch.isChecked = true
                }

            }
            else
            {
                uiThread {
                    lightSwitch.isChecked = false
                }
            }
        }
    }

    private fun sendLightMessage(isChecked : Boolean)
    {
        if (isChecked)
        {
            root_layout.setBackgroundColor(Color.GREEN)
        }
        else
        {
            root_layout.setBackgroundColor(Color.LTGRAY)
        }
        doAsync{
            if (isChecked)
            {
                val result = URL("http://$SERVER_NAME/$LIGHT_ON_PATH").readText()
                uiThread {
                    Log.wtf("Request", result)
                    longToast("Light On")
                }

            }
            else
            {
                val result = URL("http://$SERVER_NAME/$LIGHT_OFF_PATH").readText()
                uiThread {
                    Log.wtf("Request", result)
                    longToast("Light Off")
                }
            }
        }
    }
}
