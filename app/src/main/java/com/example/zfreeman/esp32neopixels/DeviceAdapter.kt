package com.example.zfreeman.esp32neopixels
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.util.Log
import kotlinx.android.synthetic.main.device_row.view.*
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class DeviceAdapter(private val devices: ArrayList<Device>, val clickListener: (Device) -> Unit) : RecyclerView.Adapter<DeviceAdapter.DeviceHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): DeviceAdapter.DeviceHolder {
        val inflatedView = p0.inflate(R.layout.device_row, false)
        return DeviceHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return devices.size
    }

    override fun onBindViewHolder(holder: DeviceAdapter.DeviceHolder, position: Int) {
        val itemDevice = devices[position]
        holder.bindDevice(itemDevice, clickListener)
    }

    //1
    class DeviceHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var device : Device? = null
        private var view: View = v

        //3
        init {
            v.setOnClickListener(this)
        }

        //4
        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
            val context = itemView.context

        }

        fun bindDevice(device : Device, clickListener: (Device) -> Unit) {
            this.device = device
            view.device_name.text = device.deviceName
            view.device_hostname.text = device.deviceHostname
            view.setOnClickListener { clickListener(device) }
        }
        companion object {
            //5
            private val DEVICE_KEY = "DEVICE"
        }
    }

}

