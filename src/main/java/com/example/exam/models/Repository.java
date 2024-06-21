package com.example.exam.models;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Repository {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	@Column(nullable = false,  unique = false)
	private String name;

    private long collaboratorId;
    @ManyToOne
    @JoinColumn(name = "collaboratorId", insertable=false, updatable=false, nullable = false, unique = false)
    private Collaborator collaborator;

    @OneToMany(mappedBy = "repository")
    private Set<Milestone> milestones = new HashSet<>();

    //Constructor
    public Repository(){
    }

    public Repository(long id, String name, Collaborator collaborator){
        this.id = id;
        this.name = name;
        this.collaborator = collaborator;
        collaboratorId = collaborator.getId();
    }

	public Repository(String name, Collaborator collaborator){
        this.name = name;
        this.collaborator = collaborator;
        collaboratorId = collaborator.getId();
    }

	//Override
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository repository = (Repository) o;
        return Objects.equals(id, repository.id);
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

    public Collaborator getCollaborator(){
        return collaborator;
    }

    public Set<Milestone> getMilestones(){
        return milestones;
    }

	//Set
	public void setName(String text){
        this.name = text;
    }

    public void setCollaborator(Collaborator collaborator){
        this.collaborator = collaborator;
        collaboratorId = collaborator.getId();
    }

    public void setMilestones(Set<Milestone> milestones){
        this.milestones = milestones;
    }
}