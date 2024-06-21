package com.example.exam.repositories;

import com.example.exam.models.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    Optional<Milestone> findByRepositoryId(Long id);
}
