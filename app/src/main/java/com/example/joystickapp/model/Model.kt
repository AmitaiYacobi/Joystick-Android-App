
package com.example.joystickapp.model

import android.widget.SeekBar
import java.io.OutputStream
import java.net.Socket
import kotlin.system.exitProcess

class Model {

    private var isConnected: Boolean = false
    private lateinit var socket: Socket
    private lateinit var out: OutputStream
    private lateinit var rudder: SeekBar
    private lateinit var throttle: SeekBar

    fun connectToFlightGear(ip: String, port: Int) {
        val thread = Thread(Runnable {
            this.socket = Socket(ip, port)
            this.out = this.socket.getOutputStream()
            this.isConnected = true
        })
        thread.start()
        thread.join()
    }

    fun setAileronValue(aileron: Float) {
        if (this.isConnected) {
            send(aileron, "aileron")
        }
    }

    fun setElevatorValue(elevator: Float) {
        if (this.isConnected) {
            send(elevator, "elevator")
        }
    }

    fun setRudderValue(rudder: Float) {
        if (isConnected) {
            send(rudder, "rudder")
        }
    }

    fun setThrottleValue(throttle: Float) {
        if (isConnected) {
            send(throttle, "throttle")
        }
    }

    private fun send(value: Float, element: String) {
        var stringToSend = ""
        val dataTransferThread = Thread(Runnable {
            when (element) {
                "aileron" -> {
                    stringToSend = "set /controls/flight/aileron $value \r\n"
                }
                "elevator" -> {
                    stringToSend = "set /controls/flight/elevator $value \r\n"
                }
                "rudder" -> {
                    stringToSend = "set /controls/flight/rudder ${value / 1000} \r\n"
                }
                "throttle" -> {
                    stringToSend = "set /controls/engines/current-engine/throttle ${value / 1000} \r\n"
                }
            }
            val toSend = stringToSend.toByteArray(Charsets.UTF_8)
            out.write(toSend)
            out.flush()
        })
        dataTransferThread.start()
        dataTransferThread.join()
    }

    fun disconnect() {
        this.out.close()
        this.socket.close()
        this.isConnected = false
    }

    fun exit() {
        exitProcess(-1)
    }
}