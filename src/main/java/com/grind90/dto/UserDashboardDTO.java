package com.grind90.dto;

public class UserDashboardDTO {

    private String userName;
    private long totalChallenges;
    private long activeChallenges;
    private int longestStreak;
    private int currentStreak;

    public UserDashboardDTO(String userName, long totalChallenges,
                            long activeChallenges, int longestStreak,
                            int currentStreak) {
        this.userName = userName;
        this.totalChallenges = totalChallenges;
        this.activeChallenges = activeChallenges;
        this.longestStreak = longestStreak;
        this.currentStreak = currentStreak;
    }

    public String getUserName() { return userName; }
    public long getTotalChallenges() { return totalChallenges; }
    public long getActiveChallenges() { return activeChallenges; }
    public int getLongestStreak() { return longestStreak; }
    public int getCurrentStreak() { return currentStreak; }
}