package com.example.zfreeman.esp32neopixels

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import java.net.URL
import kotlinx.android.synthetic.main.activity_main.*

class LightControlFragment : Fragment() {
    private lateinit var lightSwitch: Switch
    private lateinit var christmasSwitch: Switch

    companion object {
        private const val SERVER_NAME = "espressif"
        private const val LIGHT_STATUS_PATH = "lightStatus"
        private const val LIGHT_ON_PATH = "lightOn"
        private const val LIGHT_OFF_PATH = "lightOff"
        private const val CHRISTMAS_PATH = "christmas"

        private var mServerName = ""
    }
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            // Get the custom view for this fragment layout
            val view = inflater!!.inflate(R.layout.light_control_fragment, container, false)
            val bundle = arguments
            val device = bundle?.getSerializable("device")
            if (device is Device) {
                mServerName = device.deviceHostname
            }
            if (mServerName == "")
            {
                mServerName = SERVER_NAME
            }



            // Get the text view widget reference from custom layout
            lightSwitch = view.findViewById<Switch>(R.id.light_switch)
            christmasSwitch = view.findViewById<Switch>(R.id.christmas_switch)

            synchronizeSwitch()

            lightSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
                sendLightMessage(isChecked)
            }
            christmasSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
                sendChristmasMessage(isChecked)
            }

            // Return the fragment view/layout
            return view
        }

        override fun onPause() {
            super.onPause()
        }

        override fun onAttach(context: Context?) {
            super.onAttach(context)
        }

        override fun onDestroy() {
            super.onDestroy()
        }

        override fun onDetach() {
            super.onDetach()
        }

        override fun onStart() {
            super.onStart()
        }

        override fun onStop() {
            super.onStop()
        }

        private fun synchronizeSwitch() {
            doAsync {
                val statusResult = URL("http://$mServerName/$LIGHT_STATUS_PATH").readText()

                if (statusResult == "On") {
                    uiThread {
                        lightSwitch.isChecked = true
                    }

                } else {
                    uiThread {
                        lightSwitch.isChecked = false
                    }
                }
            }
        }

        private fun sendLightMessage(isChecked: Boolean) {
            doAsync {
                if (isChecked) {
                    val result = URL("http://$mServerName/$LIGHT_ON_PATH").readText()
                    uiThread {
                        Log.wtf("Request", result)
                        //longToast("Light On")
                        root_layout.setBackgroundColor(Color.GREEN)
                    }

                } else {
                    val result = URL("http://$mServerName/$LIGHT_OFF_PATH").readText()
                    uiThread {
                        Log.wtf("Request", result)
                        //longToast("Light Off")
                        root_layout.setBackgroundColor(Color.LTGRAY)
                    }
                }
            }
        }

        private fun sendChristmasMessage(isChecked: Boolean) {
            doAsync {
                if (isChecked) {
                    val result = URL("http://$mServerName/$CHRISTMAS_PATH").readText()
                    uiThread {
                        Log.wtf("Request", result)
                        //longToast("HO HO HO")
                        root_layout.setBackgroundColor(Color.GREEN)
                    }

                } else {
                    val result = URL("http://$mServerName/$LIGHT_OFF_PATH").readText()
                    uiThread {
                        Log.wtf("Request", result)
                        //longToast("Light Off")
                        root_layout.setBackgroundColor(Color.LTGRAY)
                    }
                }
            }
        }
}
