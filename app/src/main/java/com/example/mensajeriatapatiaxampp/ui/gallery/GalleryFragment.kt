package com.example.mensajeriatapatiaxampp.ui.gallery

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.mensajeriatapatiaxampp.R
import com.example.mensajeriatapatiaxampp.databinding.FragmentGalleryBinding
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private lateinit var destinatario: Spinner
    private lateinit var direccion: EditText
    private lateinit var mensajero: Spinner
    private lateinit var radioGroup: RadioGroup
    private lateinit var rbMensaje: RadioButton
    private lateinit var rbPaquete: RadioButton
    private lateinit var contenido: EditText
    private lateinit var enviar: Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val view = binding.root

        destinatario = view.findViewById(R.id.spnMenDestinatarios)
        direccion = view.findViewById(R.id.edtMenDireccion)
        mensajero = view.findViewById(R.id.spnMenMensajeros)
        radioGroup = view.findViewById(R.id.rgTipoM)
        rbMensaje = view.findViewById(R.id.rbMensaje)
        rbPaquete = view.findViewById(R.id.rbPaquete)
        contenido = view.findViewById(R.id.edtMenContenido)
        enviar = view.findViewById(R.id.btnMenEnviar)

        // Obtener destinatarios y mensajeros desde la base de datos
        obtenerDestinatarios()
        obtenerMensajeros()

        // Acción del botón enviar
        enviar.setOnClickListener {
            // Obtener los valores seleccionados de los spinners y otros campos
            val idDestinatario = destinatario.selectedItemPosition // Ejemplo: obtener el ID del destinatario seleccionado
            val idMensajero = mensajero.selectedItemPosition // Ejemplo: obtener el ID del mensajero seleccionado
            val contenidoMensaje = contenido.text.toString()
            val tipoMensaje = if (rbMensaje.isChecked) "Mensaje" else "Paquete"
            val fechaActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))

            // Insertar el mensaje en la base de datos
            insertarMensaje(idDestinatario, idMensajero, contenidoMensaje, tipoMensaje, fechaActual)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun insertarMensaje(idDestinatario: Int, idMensajero: Int, contenido: String, tipo: String, fecha: String) {
        val url = "http://192.168.1.4/insertar_mensaje.php" // URL del script PHP para insertar mensajes

        val jsonObject = JSONObject().apply {
            put("idDestinatario", idDestinatario)
            put("idMensajero", idMensajero)
            put("contenido", contenido)
            put("tipo", tipo)
            put("fecha", fecha)
        }

        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            { response ->
                // Manejar la respuesta exitosa
                Toast.makeText(context, "Mensaje insertado correctamente", Toast.LENGTH_SHORT).show()
            },
            { error ->
                // Manejar el error
                Toast.makeText(context, "Error al insertar el mensaje: ${error.message}", Toast.LENGTH_SHORT).show()
            })

        // Agregar la solicitud a la cola de solicitudes
        Volley.newRequestQueue(requireContext()).add(request)
    }


    fun obtenerDestinatarios() {
        val url = "http://192.168.137.76/obtener_destinatarios.php"

        val request = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                // Manejar la respuesta exitosa
                val listaDestinatarios = mutableListOf<String>()
                for (i in 0 until response.length()) {
                    listaDestinatarios.add(response.getString(i))
                }
                // Rellenar el spinner con la lista de destinatarios
                destinatario.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listaDestinatarios)
            },
            { error ->
                // Manejar el error
                Toast.makeText(context, "Error al obtener los destinatarios: ${error.message}", Toast.LENGTH_SHORT).show()
            })

        // Agregar la solicitud a la cola de solicitudes
        Volley.newRequestQueue(requireContext()).add(request)
    }


    fun obtenerMensajeros() {
        val url = "http://192.168.1.4/obtener_mensajeros.php" // URL del script PHP para obtener los mensajeros

        val request = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                // Manejar la respuesta exitosa
                val listaMensajeros = mutableListOf<String>()
                for (i in 0 until response.length()) {
                    val mensajero = response.getJSONObject(i)
                    listaMensajeros.add(mensajero.getString("empresa"))
                }
                // Rellenar el spinner con la lista de mensajeros
                mensajero.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listaMensajeros)
            },
            { error ->
                // Manejar el error
                Toast.makeText(context, "Error al obtener los mensajeros: ${error.message}", Toast.LENGTH_SHORT).show()
            })

        // Agregar la solicitud a la cola de solicitudes
        Volley.newRequestQueue(requireContext()).add(request)
    }

}

