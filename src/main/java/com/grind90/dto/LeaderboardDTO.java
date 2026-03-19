package com.grind90.dto;

public class LeaderboardDTO {

    private String name;
    private String challengeTitle;
    private int streak;

    public LeaderboardDTO(String name, String challengeTitle, int streak) {
        this.name = name;
        this.challengeTitle = challengeTitle;
        this.streak = streak;
    }

    public String getName() {
        return name;
    }

    public String getChallengeTitle() {
        return challengeTitle;
    }

    public int getStreak() {
        return streak;
    }
}