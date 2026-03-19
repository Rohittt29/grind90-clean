package com.grind90.model;

import jakarta.persistence.*;

@Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private int durationDays;

    public Goal() {
    }

    public Goal(String title, int durationDays) {
        this.title = title;
        this.durationDays = durationDays;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDurationDays(int durationDays) {
        this.durationDays = durationDays;
    }
}