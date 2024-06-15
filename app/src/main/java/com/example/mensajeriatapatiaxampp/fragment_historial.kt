package com.example.mensajeriatapatia

import com.example.mensajeriatapatiaxampp.R
import com.example.mensajeriatapatiaxampp.databinding.FragmentHistorialBinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.mensajeriatapatiaxampp.ClassIP
import com.example.mensajeriatapatiaxampp.Mensaje
import com.example.mensajeriatapatiaxampp.MensajeAdapter
import org.json.JSONException

class FragmentHistorial : Fragment() {

    private var _binding: FragmentHistorialBinding? = null
    private val binding get() = _binding!!

    private var Cip: ClassIP = ClassIP()
    private var ip: String = Cip.ip

    private lateinit var historialRecycleView: RecyclerView
    private lateinit var historialAdapter: MensajeAdapter // Adaptador personalizado para mensajes

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistorialBinding.inflate(inflater, container, false)
        val view = binding.root

        historialRecycleView = view.findViewById(R.id.rcvHis)
        historialRecycleView.layoutManager = LinearLayoutManager(context)
        historialAdapter = MensajeAdapter() // Instanciar el adaptador
        historialRecycleView.adapter = historialAdapter // Asignar adaptador al RecyclerView

        // Cargar mensajes desde la base de datos
        cargarMensajes()

        return view
    }

    private fun cargarMensajes() {
        val url = "http://$ip/obtener_mensaje.php"

        val request = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                try {
                    val mensajes = mutableListOf<Mensaje>()

                    for (i in 0 until response.length()) {
                        val jsonObject = response.getJSONObject(i)
                        val idMensaje = jsonObject.getInt("idMensaje")
                        val emisor = jsonObject.getString("emisor")
                        val idDestinatario = jsonObject.getInt("destinatario")
                        val idMensajero = jsonObject.getInt("mensajero")
                        val tipo = jsonObject.getString("tipo")
                        val contenido = jsonObject.getString("contenido")
                        val fecha = jsonObject.getString("fecha")

                        val mensaje = Mensaje(idMensaje, emisor, idDestinatario, idMensajero, tipo, contenido, fecha)
                        mensajes.add(mensaje)
                    }

                    historialAdapter.setItems(mensajes) // Asignar datos al adaptador

                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(context, "Error al procesar la respuesta del servidor", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                error.printStackTrace()
                Toast.makeText(context, "Error al cargar los mensajes: ${error.message}", Toast.LENGTH_SHORT).show()
            })

        // Agregar la solicitud a la cola de solicitudes
        Volley.newRequestQueue(requireContext()).add(request)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
