package com.grind90.controller;

import com.grind90.entity.Task;
import com.grind90.service.TaskService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Create task
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    // Get tasks by roadmap
    @GetMapping("/{roadmapId}")
    public List<Task> getTasksByRoadmap(@PathVariable Long roadmapId) {
        return taskService.getTasksByRoadmap(roadmapId);
    }

    // Mark task completed
    @PutMapping("/complete/{taskId}")
    public Task completeTask(@PathVariable Long taskId) {
        return taskService.markTaskCompleted(taskId);
    }
}