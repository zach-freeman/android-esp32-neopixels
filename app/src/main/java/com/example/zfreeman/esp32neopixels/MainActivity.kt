package com.example.zfreeman.esp32neopixels

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.widget.Switch
import android.widget.Toast
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import java.net.URL
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private var devicesList: ArrayList<Device> = ArrayList()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter: DeviceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        adapter = DeviceAdapter(devicesList,  { deviceItem : Device -> deviceItemClicked(deviceItem) })
        recyclerView.adapter = adapter
        setRecyclerViewItemTouchListener()
    }

    override fun onStart()
    {
        super.onStart();
        val homeDeviceJsonObject = JSONObject()
        homeDeviceJsonObject.put(Device.DEVICE_NAME, "home")
        homeDeviceJsonObject.put(Device.DEVICE_HOSTNAME, "espressif")
        val homeDevice : Device = Device(homeDeviceJsonObject)
        devicesList.add(homeDevice)
        val workDeviceJsonObject = JSONObject()
        workDeviceJsonObject.put(Device.DEVICE_NAME, "work")
        workDeviceJsonObject.put(Device.DEVICE_HOSTNAME, "192.168.1.5")
        val workDevice: Device = Device(workDeviceJsonObject)
        devicesList.add(workDevice)
    }

    private fun setRecyclerViewItemTouchListener() {
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, viewHolder1: RecyclerView.ViewHolder): Boolean {
                //2
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                //3
                return;
            }

        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun deviceItemClicked(deviceItem : Device) {
        Toast.makeText(this, "Clicked: ${deviceItem.deviceHostname}", Toast.LENGTH_LONG).show()
        val fragment = LightControlFragment()
        val args = Bundle()
        args.putSerializable("device", deviceItem)
        fragment.setArguments(args)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()

    }



}
