
package com.example.joystickapp.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs
import kotlin.math.min

class Joystick @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val outerCircle = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL_AND_STROKE
        isAntiAlias = true
    }

    private val innerCircle = Paint().apply {
        color = Color.GRAY
        style = Paint.Style.FILL_AND_STROKE
        isAntiAlias = true
    }

    private var innerRadius: Float = 0f
    private var innerCenter: PointF = PointF()
    private var outerRadius: Float = 0f
    private var outerCenter: PointF = PointF()
    private var aileron: Float = 0f
    private var elevator: Float = 0f

    lateinit var onJoystickChange: (Float, Float) -> Unit
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        innerRadius = 0.1f * min(w, h)
        innerCenter = PointF(width / 2.0f, height / 2.0f)
        outerRadius = 0.3f * min(w, h)
        outerCenter = PointF(width / 2.0f, height / 2.0f)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(outerCenter.x, outerCenter.y, outerRadius, outerCircle)
        canvas.drawCircle(innerCenter.x, innerCenter.y, innerRadius, innerCircle)
    }

    private fun move(x: Float, y: Float) {
        val distanceX = abs(x - outerCenter.x)
        val distanceY = abs(y - outerCenter.y)
        if ((outerRadius >= distanceX) && (outerRadius >= distanceY)) {
            innerCenter.x = x;
            innerCenter.y = y;

            aileron = (x - outerCenter.x) / (outerRadius)
            elevator = (outerCenter.y - y) / (outerRadius)

            onJoystickChange(aileron, elevator)
        }
        invalidate()
    }

    private fun up(x: Float, y: Float) {
        innerCenter.x = outerCenter.x;
        innerCenter.y = outerCenter.y;
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_MOVE -> move(event.x, event.y)
            MotionEvent.ACTION_UP -> up(event.x, event.y)
        }
        return true
    }
}