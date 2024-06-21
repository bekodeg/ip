package com.example.exam.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false,  unique = false)
    private String name;

    private long repositoryId;
    @ManyToOne
    @JoinColumn(name = "repositoryId", insertable=false, updatable=false, nullable = false, unique = false)
    private Repository repository;

    @OneToMany(mappedBy = "milestone")
    private Set<Issue> issues = new HashSet<>();

    public Milestone(){}

    public Milestone(String name, Repository repository){
        this.name = name;
        this.repository = repository;
        repositoryId = repository.getId();
    }

    public Milestone(long id, String name, Repository repository){
        this.id = id;
        this.name = name;
        this.repository = repository;
        repositoryId = repository.getId();
    }

    //Override
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Milestone milestone = (Milestone) o;
        return Objects.equals(id, milestone.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //Get
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Repository getRepository(){
        return repository;
    }

    public Set<Issue> getIssues(){
        return issues;
    }

    //Set
    public void setName(String name){
        this.name = name;
    }

    public void setRepository(Repository repository){
        this.repository = repository;
        repositoryId = repository.getId();
    }

    public void setIssues(Set<Issue> issues){
        this.issues = issues;
    }
}
