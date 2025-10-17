package com.example.perifericos_ejercicio.ViewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.perifericos_ejercicio.Repository.PerfilRepositorio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PerfilViewModel(
    private val repositorio: PerfilRepositorio = PerfilRepositorio()
) : ViewModel() {

    private val _imagenUri = MutableStateFlow<Uri?>(repositorio.getProfile().imagenUri)
    val imagenUri: StateFlow<Uri?> = _imagenUri

    fun setImage(uri: Uri?) {
        _imagenUri.value = uri
        repositorio.updateImage(uri)
    }
}
