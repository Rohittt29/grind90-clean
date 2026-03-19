package com.grind90.dto;

import java.time.LocalDate;

public class ChallengeResponseDTO {

    private Long id;
    private String title;
    private String goal;
    private int duration;
    private LocalDate startDate;
    private LocalDate endDate;
    private int currentStreak;
    private String userName;

    public ChallengeResponseDTO(Long id, String title, String goal,
                                int duration, LocalDate startDate,
                                LocalDate endDate, int currentStreak,
                                String userName) {

        this.id = id;
        this.title = title;
        this.goal = goal;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currentStreak = currentStreak;
        this.userName = userName;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getGoal() { return goal; }
    public int getDuration() { return duration; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public int getCurrentStreak() { return currentStreak; }
    public String getUserName() { return userName; }
}