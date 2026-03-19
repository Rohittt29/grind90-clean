package com.grind90.dto;

public class AnalyticsDTO {

    private long totalChallenges;
    private long activeChallenges;
    private int longestStreak;
    private double averageStreak;

    public AnalyticsDTO(long totalChallenges, long activeChallenges,
                        int longestStreak, double averageStreak) {
        this.totalChallenges = totalChallenges;
        this.activeChallenges = activeChallenges;
        this.longestStreak = longestStreak;
        this.averageStreak = averageStreak;
    }

    public long getTotalChallenges() { return totalChallenges; }
    public long getActiveChallenges() { return activeChallenges; }
    public int getLongestStreak() { return longestStreak; }
    public double getAverageStreak() { return averageStreak; }
}