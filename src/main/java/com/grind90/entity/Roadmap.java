package com.grind90.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Roadmap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String goal;

    private int days;

    @Column(columnDefinition = "TEXT")
    private String roadmapText;

    private LocalDate createdAt = LocalDate.now();

    // -------- STREAK SYSTEM --------

    private int currentStreak = 0;

    private int longestStreak = 0;

    private LocalDate lastCompletedDate;

    // -------- TASK RELATION --------

    @OneToMany(mappedBy = "roadmap", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public Roadmap() {}

    public Roadmap(String goal, int days, String roadmapText) {
        this.goal = goal;
        this.days = days;
        this.roadmapText = roadmapText;
    }

    // -------- GETTERS --------

    public Long getId() {
        return id;
    }

    public String getGoal() {
        return goal;
    }

    public int getDays() {
        return days;
    }

    public String getRoadmapText() {
        return roadmapText;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public int getCurrentStreak() {
        return currentStreak;
    }

    public int getLongestStreak() {
        return longestStreak;
    }

    public LocalDate getLastCompletedDate() {
        return lastCompletedDate;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    // -------- SETTERS --------

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setRoadmapText(String roadmapText) {
        this.roadmapText = roadmapText;
    }

    public void setCurrentStreak(int currentStreak) {
        this.currentStreak = currentStreak;
    }

    public void setLongestStreak(int longestStreak) {
        this.longestStreak = longestStreak;
    }

    public void setLastCompletedDate(LocalDate lastCompletedDate) {
        this.lastCompletedDate = lastCompletedDate;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}