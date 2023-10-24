package br.com.firma.todolistbackend.service;

import br.com.firma.todolistbackend.entity.Task;
import br.com.firma.todolistbackend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public Iterable<Task> allTasks() {
        return taskRepository.findAll();
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTaskById(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
