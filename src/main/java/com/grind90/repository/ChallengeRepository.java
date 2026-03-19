package com.grind90.repository;

import com.grind90.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    @Query("SELECT c FROM Challenge c JOIN FETCH c.user ORDER BY c.currentStreak DESC")
    List<Challenge> findTopChallenges();

    long count();

    long countByEndDateAfter(LocalDate date);

    @Query("SELECT MAX(c.currentStreak) FROM Challenge c")
    Integer findMaxStreak();

    @Query("SELECT AVG(c.currentStreak) FROM Challenge c")
    Double findAverageStreak();

    long countByUserId(Long userId);

    long countByUserIdAndEndDateAfter(Long userId, java.time.LocalDate date);

    @Query("SELECT MAX(c.currentStreak) FROM Challenge c WHERE c.user.id = :userId")
    Integer findMaxStreakByUserId(Long userId);

    @Query("SELECT MAX(c.currentStreak) FROM Challenge c WHERE c.user.id = :userId")
    Integer findCurrentStreakByUserId(Long userId);
}