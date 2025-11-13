package com.perez.evaluacion06

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import com.perez.evaluacion06.ui.theme.Evaluacion06Theme
import com.perez.evaluacion06.ui.cursos.CursoViewModel
import com.perez.evaluacion06.ui.cursos.CursoViewModelFactory
import com.perez.evaluacion06.ui.cursos.CursosScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Evaluacion06Theme {
                val viewModel: CursoViewModel = viewModel(
                    factory = CursoViewModelFactory(
                        (application as CursoApplication).cursoRepository
                    )
                )
                Surface(modifier = Modifier.fillMaxSize()) {
                    CursosScreen(viewModel = viewModel)
                }
            }
        }
    }
}