package com.grind90.controller;

import jakarta.validation.Valid;
import com.grind90.dto.UserDashboardDTO;
import com.grind90.dto.AnalyticsDTO;
import com.grind90.dto.ChallengeResponseDTO;
import com.grind90.dto.LeaderboardDTO;
import com.grind90.entity.Challenge;
import com.grind90.service.ChallengeService;
import com.grind90.dto.ChallengeStatsDTO;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/challenge")
public class ChallengeController {

    private final ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    // ✅ CREATE CHALLENGE (WITH VALIDATION)
    @PostMapping("/create/{userId}")
    public ChallengeResponseDTO createChallenge(
            @PathVariable Long userId,
            @Valid @RequestBody Challenge challenge
    ){
        return challengeService.createChallenge(userId, challenge);
    }

    // DAILY CHECK-IN
    @PostMapping("/checkin/{challengeId}")
    public ChallengeResponseDTO checkIn(@PathVariable Long challengeId){
        return challengeService.checkIn(challengeId);
    }

    // CHALLENGE STATS
    @GetMapping("/stats/{challengeId}")
    public ChallengeStatsDTO getStats(@PathVariable Long challengeId){
        return challengeService.getChallengeStats(challengeId);
    }

    // LEADERBOARD
    @GetMapping("/leaderboard")
    public List<LeaderboardDTO> getLeaderboard() {
        return challengeService.getLeaderboard();
    }

    // ANALYTICS
    @GetMapping("/analytics")
    public AnalyticsDTO getAnalytics() {
        return challengeService.getAnalytics();
    }

    // DASHBOARD
    @GetMapping("/dashboard/{userId}")
    public UserDashboardDTO getUserDashboard(@PathVariable Long userId) {
        return challengeService.getUserDashboard(userId);
    }
}