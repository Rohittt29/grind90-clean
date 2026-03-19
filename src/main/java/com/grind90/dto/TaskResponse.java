package com.grind90.dto;

public class TaskResponse {

    private Long id;
    private String description;
    private int dayNumber;
    private boolean completed;

    public TaskResponse() {}

    public TaskResponse(Long id, String description, int dayNumber, boolean completed) {
        this.id = id;
        this.description = description;
        this.dayNumber = dayNumber;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public boolean isCompleted() {
        return completed;
    }
}