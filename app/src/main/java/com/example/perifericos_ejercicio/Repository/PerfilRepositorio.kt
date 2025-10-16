package com.example.perifericos_ejercicio.Repository

import android.net.Uri
import com.example.perifericos_ejercicio.Model.PerfilDeUsuario
import java.net.URI


/*Parte inicial de conexion del VM*/
class PerfilRepositorio {

    private var PerfilActual = PerfilDeUsuario(
        id = 1,
        nombre = "Usuario",
        imagenUri = null
    )

    fun getProfile(): PerfilDeUsuario = PerfilActual

    fun updateImage(uri: Uri?){
        PerfilActual = PerfilActual.copy(imagenUri = uri)
    }
}