package com.example.exam.DTOs;

import com.example.exam.models.Issue;
import com.example.exam.models.Milestone;

import java.util.ArrayList;

public class IssueDTO {
    private long id;

    private String name;

    private boolean isCompleted;

    private long milestone;

    public IssueDTO(){}
    public IssueDTO(Issue issue){
        this.id = issue.getId();
        this.name = issue.getName();
        this.isCompleted = issue.isCompleted();

        milestone = issue.getMilestone().getId();
    }

    public long getId() {
        return id;
    }

    public String getName(){
        return  name;
    }

    public boolean isCompleted(){
        return isCompleted;
    }

    public long getMilestone(){
        return milestone;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setMilestone(long milestone) {
        this.milestone = milestone;
    }

    public void setName(String name) {
        this.name = name;
    }
}
