package com.example.perifericos_ejercicio.View.screen

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.perifericos_ejercicio.View.components.ImagenInteligente
import com.example.perifericos_ejercicio.ViewModel.PerfilViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun PerfilScreen(viewModel: PerfilViewModel) {
    val context = LocalContext.current
    val imagenUri by viewModel.imagenUri.collectAsState()

    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        viewModel.setImage(uri)
    }

    var cameraUri by remember { mutableStateOf<Uri?>(null) }

    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) viewModel.setImage(cameraUri)
    }

    fun createImageUri(context: Context): Uri {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file = File.createTempFile("JPEG_${timestamp}_", ".jpg", storageDir)
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ImagenInteligente(imagenUri)

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = { pickImageLauncher.launch("image/*") }) {
                Text("Selecciona tu imagen desde galería")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                when (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)) {
                    PackageManager.PERMISSION_GRANTED -> {
                        val uri = createImageUri(context)
                        cameraUri = uri
                        takePictureLauncher.launch(uri)
                    }
                    else -> {
                        // Cambié esto para que sea válido en Kotlin:
                        // “Conexión a Periféricos: Cámara 4” no puede estar solo
                        println("Conexión a Periféricos: Cámara 4")
                    }
                }
            }) {
                Text("Toma una foto con la cámara")
            }
        }
    }
}
