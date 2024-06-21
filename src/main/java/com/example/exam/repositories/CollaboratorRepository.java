package com.example.exam.repositories;

import com.example.exam.models.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CollaboratorRepository extends JpaRepository<Collaborator, Long> {
    public Optional<Collaborator> findByLoginAndPassword(String login, String password);
}
