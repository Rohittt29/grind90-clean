package com.grind90.service;

import com.grind90.dto.AiRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    public String generateAdvice(AiRequestDTO request) {

        int streak = request.getStreak();
        String goal = request.getGoal();

        if (streak == 0) {
            return "You're just starting your " + goal + " journey. Focus on consistency, not perfection.";
        }

        if (streak < 5) {
            return "Good start in " + goal + "! Build momentum by maintaining your daily habit.";
        }

        if (streak < 15) {
            return "You're getting consistent with " + goal + ". Try increasing difficulty slightly.";
        }

        if (streak < 30) {
            return "Great discipline! You're in the top zone. Avoid burnout and stay balanced.";
        }

        return "Elite level consistency! Now focus on mastery and deep understanding in " + goal + ".";
    }
}