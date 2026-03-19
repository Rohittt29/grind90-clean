package com.grind90.service;

import com.grind90.model.Goal;
import com.grind90.repository.GoalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {

    private final GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    // Create a goal
    public Goal createGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    // Fetch all goals
    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }
}