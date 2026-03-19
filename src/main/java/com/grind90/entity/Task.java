package com.grind90.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int dayNumber;

    @Column(columnDefinition = "TEXT")
    private String taskText;

    private boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "roadmap_id")
    @JsonIgnore
    private Roadmap roadmap;
    public Task() {
    }

    public Task(int dayNumber, String taskText, Roadmap roadmap) {
        this.dayNumber = dayNumber;
        this.taskText = taskText;
        this.roadmap = roadmap;
    }

    public Long getId() {
        return id;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public String getTaskText() {
        return taskText;
    }

    public boolean isCompleted() {
        return completed;
    }

    public Roadmap getRoadmap() {
        return roadmap;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}