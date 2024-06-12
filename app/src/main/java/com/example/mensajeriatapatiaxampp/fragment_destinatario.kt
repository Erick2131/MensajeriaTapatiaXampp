package com.example.mensajeriatapatiaxampp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class FragmentDestinatario : Fragment() {
    private lateinit var editTextName: EditText
    private lateinit var editTextAddress: EditText
    private lateinit var buttonAdd: Button
    private var Cip : ClassIP = ClassIP()
    private var ip: String = Cip.ip
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_destinatario, container, false)

        editTextName = view.findViewById(R.id.editTextName)
        editTextAddress = view.findViewById(R.id.editTextAddress)
        buttonAdd = view.findViewById(R.id.buttonAdd)

        buttonAdd.setOnClickListener {
            val name = editTextName.text.toString().trim()
            val address = editTextAddress.text.toString().trim()

            if (name.isEmpty()) {
                editTextName.error = "Por favor, ingrese un nombre"
                editTextName.requestFocus()
                return@setOnClickListener
            }

            if (address.isEmpty()) {
                editTextAddress.error = "Por favor, ingrese una dirección"
                editTextAddress.requestFocus()
                return@setOnClickListener
            }

            insertarDestinatario(name, address)
        }

        return view
    }

    private fun insertarDestinatario(nombre: String, direccion: String) {
        //val url = "http://192.168.137.76/insert_destinatario.php"
        val url = "http://$ip/insert_destinatario.php"
        val stringRequest = object : StringRequest(
            Request.Method.POST, url, Response.Listener
            { response ->
                // Manejar la respuesta exitosa
                Toast.makeText(context, "Destinatario insertado correctamente", Toast.LENGTH_SHORT).show()
                // Limpiar los campos de entrada
                editTextName.text.clear()
                editTextAddress.text.clear()
            },
            Response.ErrorListener{ error ->
                // Manejar el error
                error.printStackTrace()
                if (error.networkResponse != null && error.networkResponse.data != null) {
                    val errorMsg = String(error.networkResponse.data)
                    Toast.makeText(context, "Error al insertar el destinatario: $errorMsg", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Error al insertar el destinatario: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            }) {

            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["nombre"] = nombre
                params["direccion"] = direccion
                return params
            }
        }

        // Agregar la solicitud a la cola de solicitudes
        Volley.newRequestQueue(requireContext()).add(stringRequest)
    }
}
