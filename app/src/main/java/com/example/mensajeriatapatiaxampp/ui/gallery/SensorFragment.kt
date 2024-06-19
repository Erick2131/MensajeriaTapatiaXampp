package com.example.mensajeriatapatiaxampp.ui.gallery

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.mensajeriatapatiaxampp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SensorFragment : Fragment(),SensorEventListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var sensor: android.hardware.Sensor?  = null
    private lateinit var sensorManager: SensorManager
    var existeSensorProximidad : Boolean = false
    private lateinit var proximityTextView : TextView
    private lateinit var btnProxi: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sensor, container, false)
        proximityTextView = view.findViewById(R.id.proximityTextView)
        btnProxi = view.findViewById(R.id.btnSensorProxi)
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager

        btnProxi.setOnClickListener { botonProximidad() }

        return view
    }
    private fun botonProximidad() {
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
            existeSensorProximidad = true
            proximityTextView.text = "El dispositivo tiene sensor: ${sensor!!.name}"
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        } else {
            proximityTextView.text = "No se cuenta con sensor de proximidad"
            existeSensorProximidad = false
        }
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SensorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onSensorChanged(event: SensorEvent) {
        // Obtiene el valor que arroja el sensor
        val valorCambio: Float
        // Evaluar el valor para realizar alguna acción
        if (existeSensorProximidad) {
            valorCambio = event.values[0]
            if (valorCambio < 1.0) {
                proximityTextView.textSize = 30f
                proximityTextView.text = "\nCERCA"
            } else {
                proximityTextView.textSize = 30f
                proximityTextView.text = "\nLEJOS"
            }
        } else {
            //Toast.makeText(context, "Sin cambios.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onAccuracyChanged(sensor: android.hardware.Sensor?, accuracy: Int) {

    }
}