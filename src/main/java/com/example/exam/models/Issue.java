package com.example.exam.models;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false,  unique = false)
    private String name;

    @Column(nullable = false,  unique = false)
    private boolean isCompleted;


    private long milestoneId;
    @ManyToOne
    @JoinColumn(name = "milestoneId", nullable = false, unique = false)
    private Milestone milestone;

    //Constructor
    public Issue(){}

    public Issue(String name, Milestone milestone){
        this.milestone = milestone;
        milestoneId = milestone.getId();
        this.name = name;
        isCompleted = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return Objects.equals(id, issue.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //Get
    public long getId() {
        return id;
    }

    public String getName(){return name;}

    public boolean isCompleted() {
        return isCompleted;
    }

    public Milestone getMilestone(){
        return milestone;
    }

    //Set
    public void setName(String text){
        this.name = text;
    }

    public void setCompleted(boolean isCompleted){
        this.isCompleted = isCompleted;
    }


    public void setMilestone(Milestone milestone){
        this.milestone = milestone;
        milestoneId = milestone.getId();
    }
}
