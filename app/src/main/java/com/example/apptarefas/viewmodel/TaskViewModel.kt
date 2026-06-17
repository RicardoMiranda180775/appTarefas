package com.example.apptarefas.viewmodel

import com.example.apptarefas.data.Task
import com.example.apptarefas.data.TaskRepository
import com.example.apptarefas.data.TaskStatus

class TaskViewModel {

    private val repository = TaskRepository()

    fun loadTasks(): List<Task>{
        return repository.getTasks()
    }

    fun addTask(title: String, description: String){
        repository.add(
            Task(
                repository.getTasks().size + 1,
                title,
                description,
                TaskStatus.TODO
            )
        )
    }

    fun deleteTask(task: Task){
        repository.delete(task)
    }

    fun updateTask(task: Task,
                   newTitle: String
    ){
        repository.update(task, newTitle)
    }
}