package com.example.joystickapp.views


import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.joystickapp.R
import com.example.joystickapp.view_model.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val connectButton: Button = findViewById(R.id.btnConnect)
        val disconnectButton: Button = findViewById(R.id.disconnect)
        val exitButton: Button = findViewById(R.id.Exit)
        val rudderBar: SeekBar = findViewById(R.id.rudder)
        val throttleBar: SeekBar = findViewById(R.id.throttle)
        val joystick: Joystick = findViewById(R.id.joystick)
        rudderBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                viewModel.setRudder(progress.toFloat())
            }
        })
        throttleBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                viewModel.setThrottle(progress.toFloat())
            }
        })
        joystick.onJoystickChange = { aileron: Float, elevator: Float ->
            viewModel.setAileron(aileron)
            viewModel.setElevator(elevator)
        }
        connectButton.setOnClickListener { connectToFlightGear() }
        disconnectButton.setOnClickListener { disconnect() }
        exitButton.setOnClickListener { exit() }
    }

    private fun connectToFlightGear() {
        val statusMessage = findViewById<View>(R.id.message) as TextView
        try {
            val ip = findViewById<EditText>(R.id.etIPAddress).text.toString()
            val port = findViewById<EditText>(R.id.etPort).text.toString().toInt()
            viewModel.connectToFlightGear(ip, port)
            statusMessage.text = "Connected!"
        } catch (e: Exception) {
            e.printStackTrace()
            statusMessage.setTextColor(Color.RED)
            statusMessage.text = "Connection failed! Make sure your IP address and PORT are valid."
        }
    }

    private fun disconnect() {
        val statusMessage = findViewById<View>(R.id.message) as TextView
        viewModel.disconnect()
        statusMessage.text = "Disconnected. To start again, press on the CONNECT button."
    }

    private fun exit() {
        viewModel.exit()
    }
}