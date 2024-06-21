package com.example.exam.repositories;

import com.example.exam.models.Issue;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    Page<Issue> findAllByMilestoneId(Long id, Pageable pageable);

    @NotNull Page<Issue> findAll(@NotNull Pageable pageable);
}
