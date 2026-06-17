package com.example.apptarefas.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.*
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.apptarefas.data.Task
import com.example.apptarefas.viewmodel.TaskViewModel
import androidx.compose.runtime.*
import com.example.apptarefas.data.TaskStatus

@Composable
fun TaskScreen(viewModel: TaskViewModel){

    var tasksList by remember {mutableStateOf(viewModel.loadTasks())}

    var text by remember { mutableStateOf("") }

    var editText by remember { mutableStateOf("")}

    var showDialog by remember { mutableStateOf(false) }

    var selectedTask by remember { mutableStateOf<Task?>(null) }

    var description by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Minhas Tarefas",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        tasksList.forEach { task ->

            TaskItem(
                task = task,
                onTaskClick = {
                    selectedTask = it
                    editText = it.title
                    showDialog = true
                }
            )
        }

        Column {

            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Nova Tarefa")
                }
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            TextField(
                value = description,
                onValueChange = {
                    description = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Descrição")
                }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if(text.isNotBlank()){
                viewModel.addTask(text, description)
                tasksList = viewModel.loadTasks()
                text = ""
                description = ""
            }
        }) {
            Text("+")
        }
    }

    if(showDialog){
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },

            title = {
                Text("Editar tarefa?")
            },

            text = {
                TextField(
                    value = editText,

                    onValueChange = {
                        editText = it
                    },

                    label = {
                        Text("Editar tarefa")
                    }
                )
            },

            confirmButton = {
                Button(
                    onClick = {
                        viewModel.updateTask(selectedTask!!, editText)
                        tasksList = viewModel.loadTasks()
                        showDialog = false
                    }
                ) {
                    Text("Salvar")
                }
            },

            dismissButton = {
                Button(
                    onClick = {

                        viewModel.deleteTask(selectedTask!!)
                        showDialog = false
                    }
                ) {
                    Text("Excluir")
                }


                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun TaskItem(
    task: Task,
    onTaskClick: (Task) -> Unit,
    ){
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)
        .clickable{
        onTaskClick(task)
    }
    ){

        Column(
            modifier = Modifier.weight(1f)
                .clickable{
                    onTaskClick(task)
                }
        ) {

            Text(
                text = task.title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = task.description
            )

            Text(
                text = when(task.status){
                TaskStatus.TODO -> "A Fazer"
                TaskStatus.IN_PROGRESS -> "Em Andamento"
                TaskStatus.DONE -> "✅ Concluída"
                TaskStatus.CANCELED -> "❌ Cancelada"
            },
            )
        }


//        Text(
//            text = task.title,
//            modifier = Modifier.weight(1f)
//                .fillMaxWidth()
//                .clickable{
//                    onTaskClick(task)
//                }
//                .padding(12.dp)
//        )
//        Text(
//            text = task.description,
//            modifier = Modifier.weight(1f)
//                .fillMaxWidth()
//                .clickable{
//                    onTaskClick(task)
//                }
//                .padding(12.dp)
//        )
//
//        Text(
//            text = when(task.status){
//                TaskStatus.TODO -> "A Fazer"
//                TaskStatus.IN_PROGRESS -> "Em Andamento"
//                TaskStatus.DONE -> "✅ Concluída"
//                TaskStatus.CANCELED -> "❌ Cancelada"
//            },
//        )
    }
}

