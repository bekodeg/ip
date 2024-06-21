package com.example.exam.DTOs;

import com.example.exam.models.Issue;
import com.example.exam.models.Milestone;

import java.util.ArrayList;
import java.util.List;

public class MilestoneDTO {
    private long id;
    private String name;

    private Long repository;

    private List<Long> issues;

    //Constructor
    public MilestoneDTO(){}
    public MilestoneDTO(Milestone milestone){
        this.id = milestone.getId();
        this.name = milestone.getName();
        this.repository = milestone.getRepository().getId();

        if (milestone.getIssues() != null){
            this.issues = milestone.getIssues().stream().map(Issue::getId).toList();
        }
        else {
            this.issues = new ArrayList<>();
        }
    }
    //Get
    public long getId() {return id;}
    public String getName(){return name;}

    public Long getRepository(){
        return repository;
    }

    public List<Long> getMilestones(){
        return issues;
    }

    //Set
    public void setName(String name){
        this.name = name;
    }

    public void setRepository(Long repository){
        this.repository = repository;
    }

    public void setIssues(List<Long> issues){
        this.issues = issues;
    }
}
