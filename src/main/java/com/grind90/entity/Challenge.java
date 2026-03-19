package com.grind90.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "challenges")
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title must not be blank")
    private String title;

    @NotBlank(message = "Goal must not be blank")
    private String goal;

    @Min(value = 1, message = "Duration must be at least 1 day")
    private int duration;

    private LocalDate startDate;

    private LocalDate endDate;

    private int currentStreak;

    private LocalDate lastCheckInDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Challenge() {}

    // ✅ Getters & Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGoal() { return goal; }
    public void setGoal(String goal) { this.goal = goal; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public int getCurrentStreak() { return currentStreak; }
    public void setCurrentStreak(int currentStreak) { this.currentStreak = currentStreak; }

    public LocalDate getLastCheckInDate() { return lastCheckInDate; }
    public void setLastCheckInDate(LocalDate lastCheckInDate) { this.lastCheckInDate = lastCheckInDate; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}