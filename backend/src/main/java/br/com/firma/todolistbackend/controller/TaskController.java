package br.com.firma.todolistbackend.controller;

import br.com.firma.todolistbackend.dto.TaskDTO;
import br.com.firma.todolistbackend.entity.Task;
import br.com.firma.todolistbackend.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@CrossOrigin
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping()
    Iterable<Task> allTasks() {
        return taskService.allTasks();
    }

    @PostMapping
    Task saveTask(@RequestBody @Valid TaskDTO taskDTO) {
        Task task = taskDTO.toTask();
        return taskService.saveTask(task);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTaskById(taskId);
    }
}
