package com.grind90.dto;

public class ChallengeStatsDTO {

    private int currentStreak;
    private long daysCompleted;
    private long daysRemaining;
    private double completionPercentage;

    public ChallengeStatsDTO(int currentStreak, long daysCompleted, long daysRemaining, double completionPercentage) {
        this.currentStreak = currentStreak;
        this.daysCompleted = daysCompleted;
        this.daysRemaining = daysRemaining;
        this.completionPercentage = completionPercentage;
    }

    public int getCurrentStreak() {
        return currentStreak;
    }

    public long getDaysCompleted() {
        return daysCompleted;
    }

    public long getDaysRemaining() {
        return daysRemaining;
    }

    public double getCompletionPercentage() {
        return completionPercentage;
    }
}