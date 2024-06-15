package com.example.mensajeriatapatia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.mensajeriatapatiaxampp.ClassIP
import com.example.mensajeriatapatiaxampp.R
import com.example.mensajeriatapatiaxampp.databinding.FragmentBusquedaBinding
import com.example.mensajeriatapatiaxampp.databinding.FragmentMensajeroBinding
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class FragmentBusqueda : Fragment() {

    private var _binding: FragmentBusquedaBinding? = null
    private val binding get() = _binding!!

    private var Cip: ClassIP = ClassIP()
    private var ip: String = Cip.ip

    private lateinit var editTextId: EditText
    private lateinit var resultado: TextView
    private lateinit var buttonBuscar: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBusquedaBinding.inflate(inflater, container, false)
        val view = binding.root

        editTextId = view.findViewById(R.id.edtBusBusqueda)
        resultado = view.findViewById(R.id.txtBusResult)
        buttonBuscar = view.findViewById(R.id.btnBusBuscar)

        buttonBuscar.setOnClickListener {
            val idMensaje = editTextId.text.toString().trim()

            if (idMensaje.isNotEmpty()) {
                buscarMensaje(idMensaje)
            } else {
                Toast.makeText(requireContext(), "Ingrese un ID de mensaje válido", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun buscarMensaje(idMensaje: String) {
        val url = "http://$ip/buscar_mensaje.php?idMensaje=$idMensaje"

        val request = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                try {
                    // Verificar si se encontraron resultados
                    if (response.length() > 0) {
                        val mensaje = response.getJSONObject(0)
                        val contenido = mensaje.getString("contenido")
                        val tipo = mensaje.getString("tipo")
                        val fecha = mensaje.getString("fecha")

                        val mensajeString = "Contenido: $contenido\nTipo: $tipo\nFecha: $fecha"
                        resultado.text = mensajeString
                    } else {
                        resultado.text = "Mensaje no encontrado"
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(requireContext(), "Error al procesar la respuesta del servidor", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                error.printStackTrace()
                Toast.makeText(requireContext(), "Error al buscar el mensaje: ${error.message}", Toast.LENGTH_SHORT).show()
            })

        // Agregar la solicitud a la cola de solicitudes
        Volley.newRequestQueue(requireContext()).add(request)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
