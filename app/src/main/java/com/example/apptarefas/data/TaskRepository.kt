package com.example.apptarefas.data

class TaskRepository {

    private val tasks = mutableListOf(
        Task(1, "Praticar Kotlin", "Desc1", TaskStatus.TODO),
        Task(2, "Estudar Arquitetura",  "Desc2",TaskStatus.TODO),
        Task(3, "Ler sobre desenv movel",  "Desc3",TaskStatus.IN_PROGRESS),
        Task(4, "Revisar aulas anteriores",  "Desc4",TaskStatus.CANCELED),
        Task(5, "Estudar Compose",  "Desc5",TaskStatus.DONE)
    )

    fun getTasks(): List<Task>{
        return tasks
    }

    fun add(task: Task){
        tasks.add(task)
        println("Tamanho após add: ${tasks.size}")
    }

    fun delete(task: Task) {
        tasks.removeIf {
            it.id == task.id
        }
    }

    fun update(task: Task, newTitle: String){
        val index = tasks.indexOfFirst { it.id == task.id }
        //tasks[index] = task
        if(index != -1) {
            tasks[index] = tasks[index].copy(
                title = newTitle
            )
        }
    }

    fun getTasksDatabase(): List<Task>{
        return listOf(
        )
    }

    fun getTasksApi(): List<Task>{
        return listOf(
        )
    }
}