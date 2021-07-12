package com.example.joystickapp.view_model

import androidx.lifecycle.ViewModel
import com.example.joystickapp.model.Model

class MainViewModel : ViewModel() {

    private val model: Model = Model()

    fun connectToFlightGear(ip: String, port: Int) {
        model.connectToFlightGear(ip, port)
    }

    fun setAileron(aileron: Float) {
        model.setAileronValue(aileron)
    }

    fun setElevator(elevator: Float) {
        model.setElevatorValue(elevator)
    }

    fun setRudder(rudder: Float) {
        model.setRudderValue(rudder)
    }

    fun setThrottle(throttle: Float) {
        model.setThrottleValue(throttle)
    }

    fun exit() {
        model.exit()
    }

    fun disconnect() {
        model.disconnect()
    }
}