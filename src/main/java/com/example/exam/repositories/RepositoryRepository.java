package com.example.exam.repositories;

import com.example.exam.models.Repository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RepositoryRepository extends JpaRepository<Repository, Long> {
    Page<Repository> findAllByCollaboratorId(Long id, Pageable pageable);

    @NotNull Page<Repository> findAll(@NotNull Pageable pageable);
}
