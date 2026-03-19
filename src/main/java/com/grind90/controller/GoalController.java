package com.grind90.controller;

import com.grind90.dto.GoalProgress;
import com.grind90.entity.Task;
import com.grind90.service.TaskService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
@CrossOrigin
public class GoalController {

    private final TaskService taskService;

    public GoalController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{roadmapId}/tasks")
    public List<Task> getTasks(@PathVariable Long roadmapId) {
        return taskService.getTasksByRoadmap(roadmapId);
    }

    @GetMapping("/{roadmapId}/progress")
    public GoalProgress getProgress(@PathVariable Long roadmapId) {
        return taskService.getGoalProgress(roadmapId);
    }
}