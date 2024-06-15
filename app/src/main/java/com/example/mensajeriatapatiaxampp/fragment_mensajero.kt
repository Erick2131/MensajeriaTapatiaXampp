package com.example.mensajeriatapatia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mensajeriatapatiaxampp.ClassIP
import com.example.mensajeriatapatiaxampp.R
import com.example.mensajeriatapatiaxampp.databinding.FragmentGalleryBinding
import com.example.mensajeriatapatiaxampp.databinding.FragmentMensajeroBinding

class fragment_mensajero : Fragment() {
    private var _binding: FragmentMensajeroBinding? = null
    private val binding get() = _binding!!

    private var Cip : ClassIP = ClassIP()
    private var ip: String = Cip.ip

    private lateinit var editTextEmpresa: EditText
    private lateinit var editTextVehiculo: EditText
    private lateinit var ratingBar: RatingBar
    private lateinit var buttonAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMensajeroBinding.inflate(inflater, container, false)
        val view = binding.root

        editTextEmpresa = view.findViewById(R.id.edtMjrEmpresa)
        editTextVehiculo = view.findViewById(R.id.edtMjrVehiculo)
        ratingBar = view.findViewById(R.id.ratingBar)
        buttonAdd = view.findViewById(R.id.btnMjrAdd)

        buttonAdd.setOnClickListener {
            val empresa = editTextEmpresa.text.toString()
            val vehiculo = editTextVehiculo.text.toString()
            val valoraciones = ratingBar.rating.toDouble()

            insertarMensajero(empresa, vehiculo, valoraciones)
        }

        return view
    }

    private fun insertarMensajero(empresa: String, vehiculo: String, valoraciones: Double) {
        val url = "http://$ip/insertar_mensajero.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                Toast.makeText(context, "Mensajero insertado Correctamente", Toast.LENGTH_LONG).show()
            },
            { error ->
                error.printStackTrace()
                if (error.networkResponse != null && error.networkResponse.data != null) {
                    val errorMsg = String(error.networkResponse.data)
                    Toast.makeText(context, "Error al insertar el mensajero: $errorMsg", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Error al insertar el mensajero: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            }) {

            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["empresa"] = empresa
                params["vehiculo"] = vehiculo
                params["valoraciones"] = valoraciones.toString()
                return params
            }
        }

        Volley.newRequestQueue(requireContext()).add(stringRequest)
    }

}
