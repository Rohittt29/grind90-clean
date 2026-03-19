package com.grind90.service;

import com.grind90.entity.Roadmap;
import com.grind90.entity.Task;
import com.grind90.repository.RoadmapRepository;
import com.grind90.repository.TaskRepository;
import com.grind90.dto.GoalProgress;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final RoadmapRepository roadmapRepository;

    public TaskService(TaskRepository taskRepository, RoadmapRepository roadmapRepository) {
        this.taskRepository = taskRepository;
        this.roadmapRepository = roadmapRepository;
    }

    // Create task
    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    // Get tasks by roadmap
    public List<Task> getTasksByRoadmap(Long roadmapId){
        return taskRepository.findByRoadmapId(roadmapId);
    }

    // Complete task + update streak
    public Task markTaskCompleted(Long taskId){

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setCompleted(true);

        Roadmap roadmap = task.getRoadmap();

        LocalDate today = LocalDate.now();
        LocalDate lastCompleted = roadmap.getLastCompletedDate();

        if(lastCompleted == null){
            roadmap.setCurrentStreak(1);
        }
        else if(lastCompleted.plusDays(1).equals(today)){
            roadmap.setCurrentStreak(roadmap.getCurrentStreak() + 1);
        }
        else if(lastCompleted.equals(today)){
            // same day completion → do nothing
        }
        else{
            roadmap.setCurrentStreak(1);
        }

        if(roadmap.getCurrentStreak() > roadmap.getLongestStreak()){
            roadmap.setLongestStreak(roadmap.getCurrentStreak());
        }

        roadmap.setLastCompletedDate(today);

        roadmapRepository.save(roadmap);

        return taskRepository.save(task);
    }

    // Progress calculation
    public GoalProgress getGoalProgress(Long roadmapId){

        long totalTasks = taskRepository.countByRoadmapId(roadmapId);
        long completedTasks = taskRepository.countByRoadmapIdAndCompletedTrue(roadmapId);

        int percentage = 0;

        if(totalTasks > 0){
            percentage = (int)((completedTasks * 100) / totalTasks);
        }

        return new GoalProgress(
                (int) totalTasks,
                (int) completedTasks,
                percentage + "%"
        );
    }
}