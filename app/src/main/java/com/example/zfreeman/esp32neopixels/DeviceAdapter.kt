package com.example.zfreeman.esp32neopixels
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.util.Log
import kotlinx.android.synthetic.main.device_row.view.*

class DeviceAdapter(private val devices: ArrayList<Device>) : RecyclerView.Adapter<DeviceAdapter.DeviceHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): DeviceAdapter.DeviceHolder {
        val inflatedView = p0.inflate(R.layout.device_row, false)
        return DeviceHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: DeviceAdapter.DeviceHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //1
    class DeviceHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        //2
        private var view: View = v

        //3
        init {
            v.setOnClickListener(this)
        }

        //4
        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
        }

        companion object {
            //5
            private val DEVICE_KEY = "PHOTO"
        }
    }

}

