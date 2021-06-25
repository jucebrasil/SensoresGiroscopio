package br.edu.uea.hefesto4.sensorgiroscopio_jucelbia

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {
    // Parei 9:40 do segundo video
    private lateinit var sensorManager: SensorManager
    private lateinit var gyroscopeSensor: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, gyroscopeSensor,
                SensorManager.SENSOR_DELAY_NORMAL )
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {// passa a precisão

    }

    override fun onSensorChanged(event: SensorEvent?) {
        val x = event?.values!![0]
        val y = event?.values!![1]
        val z = event?.values!![2]
        textView.text = "x = %.1f rad/s \n".format(x) + "y = %.1f rad/s \n".format(y) +
                "z = %.1f rad/s".format(z)

    }
}