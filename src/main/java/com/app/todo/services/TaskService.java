package com.app.todo.services;


import com.app.todo.models.Task;
import com.app.todo.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void createTask(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        taskRepository.save(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public void toggle(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid task"));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }
}
