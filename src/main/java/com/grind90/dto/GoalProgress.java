package com.grind90.dto;

public class GoalProgress {

    private int totalTasks;
    private int completedTasks;
    private String progress;

    public GoalProgress(int totalTasks, int completedTasks, String progress) {
        this.totalTasks = totalTasks;
        this.completedTasks = completedTasks;
        this.progress = progress;
    }

    public int getTotalTasks() {
        return totalTasks;
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    public String getProgress() {
        return progress;
    }
}