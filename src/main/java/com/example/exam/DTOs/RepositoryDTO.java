package com.example.exam.DTOs;

import com.example.exam.models.Milestone;
import com.example.exam.models.Repository;

import java.util.*;

public class RepositoryDTO {
    private long id;
	private String name;

	private Long collaborator;

	private List<Long> milestones;

	//Constructor
    public RepositoryDTO(){}
    public RepositoryDTO(Repository repository){
        this.id = repository.getId();
		this.name = repository.getName();
		this.collaborator = repository.getCollaborator().getId();

		if (repository.getMilestones() != null){
			this.milestones = repository.getMilestones().stream().map(Milestone::getId).toList();
		}
		else {
			this.milestones = new ArrayList<>();
		}
    }
    //Get
    public long getId() {return id;}
	public String getName(){return name;}

	public Long getCollaborator(){
		return collaborator;
	}

	public List<Long> getMilestones(){
		return milestones;
	}

	//Set
	public void setName(String name){
		this.name = name;
	}

	public void setCollaborator(Long collaborator){
		this.collaborator = collaborator;
	}

	public void setMilestones(List<Long> milestones){
		this.milestones = milestones;
	}
}