package com.example.perifericos_ejercicio.Model

import android.net.Uri


/*ACA SE ALMACENA LA INFO DE NUESTRA IMAGEN*/
data class PerfilDeUsuario(
    val id : Int,
    val nombre : String,
    val imagenUri : Uri? = null /*Cadena que identifica de forma unica*/
)
