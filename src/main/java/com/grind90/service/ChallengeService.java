package com.grind90.service;

import com.grind90.dto.*;
import com.grind90.entity.Challenge;
import com.grind90.entity.User;
import com.grind90.exception.ResourceNotFoundException;
import com.grind90.repository.ChallengeRepository;
import com.grind90.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final UserRepository userRepository;

    public ChallengeService(ChallengeRepository challengeRepository,
                            UserRepository userRepository) {
        this.challengeRepository = challengeRepository;
        this.userRepository = userRepository;
    }

    // CREATE CHALLENGE FOR A USER
    public ChallengeResponseDTO createChallenge(Long userId, Challenge challenge){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        challenge.setUser(user);
        challenge.setStartDate(LocalDate.now());
        challenge.setEndDate(LocalDate.now().plusDays(challenge.getDuration()));
        challenge.setCurrentStreak(0);

        Challenge updated = challengeRepository.save(challenge);

        return new ChallengeResponseDTO(
                updated.getId(),
                updated.getTitle(),
                updated.getGoal(),
                updated.getDuration(),
                updated.getStartDate(),
                updated.getEndDate(),
                updated.getCurrentStreak(),
                updated.getUser().getName()
        );
    }

    // DAILY CHECK-IN
    public ChallengeResponseDTO checkIn(Long challengeId){

        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new RuntimeException("Challenge not found"));

        LocalDate today = LocalDate.now();

        if (challenge.getLastCheckInDate() != null &&
                challenge.getLastCheckInDate().equals(today)) {
            return mapToDTO(challenge);
        }

        if (challenge.getLastCheckInDate() != null &&
                challenge.getLastCheckInDate().plusDays(1).equals(today)) {
            challenge.setCurrentStreak(challenge.getCurrentStreak() + 1);
        } else {
            challenge.setCurrentStreak(1);
        }

        challenge.setLastCheckInDate(today);

        Challenge updated = challengeRepository.save(challenge);

        return mapToDTO(updated);
    }

    // CHALLENGE STATS
    public ChallengeStatsDTO getChallengeStats(Long challengeId){

        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new RuntimeException("Challenge not found"));

        long daysCompleted = ChronoUnit.DAYS.between(
                challenge.getStartDate(), LocalDate.now());

        long daysRemaining = ChronoUnit.DAYS.between(
                LocalDate.now(), challenge.getEndDate());

        double completionPercentage =
                ((double) daysCompleted / challenge.getDuration()) * 100;

        return new ChallengeStatsDTO(
                challenge.getCurrentStreak(),
                daysCompleted,
                daysRemaining,
                completionPercentage
        );
    }

    public List<LeaderboardDTO> getLeaderboard() {

        return challengeRepository.findTopChallenges()
                .stream()
                .filter(c -> c.getUser() != null) // avoid null crash
                .map(c -> new LeaderboardDTO(
                        c.getUser().getName(),
                        c.getTitle(),
                        c.getCurrentStreak()
                ))
                .toList();
    }

    private ChallengeResponseDTO mapToDTO(Challenge challenge) {
        return new ChallengeResponseDTO(
                challenge.getId(),
                challenge.getTitle(),
                challenge.getGoal(),
                challenge.getDuration(),
                challenge.getStartDate(),
                challenge.getEndDate(),
                challenge.getCurrentStreak(),
                challenge.getUser() != null ? challenge.getUser().getName() : "Unknown"
        );
    }

    public AnalyticsDTO getAnalytics() {

        long total = challengeRepository.count();

        long active = challengeRepository.countByEndDateAfter(LocalDate.now());

        Integer maxStreak = challengeRepository.findMaxStreak();
        Double avgStreak = challengeRepository.findAverageStreak();

        return new AnalyticsDTO(
                total,
                active,
                maxStreak != null ? maxStreak : 0,
                avgStreak != null ? avgStreak : 0.0
        );
    }

    public UserDashboardDTO getUserDashboard(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        long total = challengeRepository.countByUserId(userId);

        long active = challengeRepository.countByUserIdAndEndDateAfter(
                userId, LocalDate.now()
        );

        Integer longest = challengeRepository.findMaxStreakByUserId(userId);

        Integer current = challengeRepository.findCurrentStreakByUserId(userId);

        return new UserDashboardDTO(
                user.getName(),
                total,
                active,
                longest != null ? longest : 0,
                current != null ? current : 0
        );
    }
}