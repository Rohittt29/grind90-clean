package com.grind90.repository;

import com.grind90.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByRoadmapId(Long roadmapId);

    long countByRoadmapId(Long roadmapId);

    long countByRoadmapIdAndCompletedTrue(Long roadmapId);

    List<Task> findByCompletedFalse();
}