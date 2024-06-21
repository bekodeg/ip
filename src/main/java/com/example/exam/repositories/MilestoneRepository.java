package com.example.exam.repositories;

import com.example.exam.models.Milestone;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    Page<Milestone> findAllByRepositoryId(Long id, Pageable pageable);
    @NotNull Page<Milestone> findAll(@NotNull Pageable pageable);
}
