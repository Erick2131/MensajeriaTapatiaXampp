package com.example.mensajeriatapatia

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.NetworkResponse
import com.android.volley.ParseError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.mensajeriatapatiaxampp.ClassIP
import com.example.mensajeriatapatiaxampp.R
import com.example.mensajeriatapatiaxampp.databinding.FragmentBusquedaBinding
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import android.util.Base64
import kotlin.io.encoding.ExperimentalEncodingApi


class FragmentBusqueda : Fragment() {

    private var _binding: FragmentBusquedaBinding? = null
    private val binding get() = _binding!!

    private var Cip: ClassIP = ClassIP()
    private var ip: String = Cip.ip

    private lateinit var editTextId: EditText
    private lateinit var resultado: TextView
    private lateinit var buttonBuscar: Button
    private lateinit var imgFoto: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBusquedaBinding.inflate(inflater, container, false)
        val view = binding.root

        editTextId = view.findViewById(R.id.edtBusBusqueda)
        resultado = view.findViewById(R.id.txtBusResult)
        buttonBuscar = view.findViewById(R.id.btnBusBuscar)
        imgFoto = view.findViewById(R.id.imgBusFoto)

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

    @OptIn(ExperimentalEncodingApi::class)
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
                        val foto = mensaje.getString("foto")
                        if(foto.isNotEmpty()){
                            val encodedImage = Base64.decode(foto, Base64.DEFAULT)
                            val fotoBitmap = byteArrayToBitmap(encodedImage)
                            imgFoto.setImageBitmap(fotoBitmap)
                        }

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

    fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}
