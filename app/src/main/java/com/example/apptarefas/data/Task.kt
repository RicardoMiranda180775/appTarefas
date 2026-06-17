package com.example.apptarefas.data

import android.R

data class Task (
    val id: Int,
    var title: String,
    var description: String,
    val status: TaskStatus
)


enum class TaskStatus {
    TODO,
    IN_PROGRESS,
    DONE,
    CANCELED
}
