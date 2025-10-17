package com.example.perifericos_ejercicio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.perifericos_ejercicio.View.screen.PerfilScreen
import com.example.perifericos_ejercicio.ViewModel.PerfilViewModel
import com.example.perifericos_ejercicio.ui.theme.PerifericosEjercicioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val perfilViewModel = PerfilViewModel()
        setContent {
            PerifericosEjercicioTheme {
                PerfilScreen(viewModel = perfilViewModel)
            }
        }
    }
}
