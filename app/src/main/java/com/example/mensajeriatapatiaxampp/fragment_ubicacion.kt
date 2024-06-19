package com.example.mensajeriatapatiaxampp

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class fragment_ubicacion : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        try {
            val ceti = LatLng(20.702264, -103.388345)
            googleMap.addMarker(MarkerOptions().position(ceti).title("Encuentranos en: "))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ceti, 15f))
        } catch (e: Exception) {
            Log.e("MapError", "Error loading map", e)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ubicacion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}