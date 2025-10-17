package com.example.perifericos_ejercicio.Model

import android.content.Context
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.perifericos_ejercicio.ViewModel.PerfilViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "preferences-usuario")
val IMAGE_URI_KEY = stringPreferencesKey("image_uri")
class ImagenDataStore(private val context: Context) {


val imagenUriFlow: Flow<String?> = context.dataStore.data
    .map { preferences : Preferences ->
        preferences[IMAGE_URI_KEY]
    }

suspend fun guardarImagenUri( uri: String){
    context.dataStore.edit { preferences ->
        preferences[IMAGE_URI_KEY] = uri
    }
}

}