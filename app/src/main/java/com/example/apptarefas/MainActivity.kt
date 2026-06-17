package com.example.apptarefas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.apptarefas.ui.TaskScreen
import com.example.apptarefas.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel = TaskViewModel()
            TaskScreen(viewModel)
        }
    }
}

