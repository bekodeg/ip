package com.example.exam.DTOs;

import com.example.exam.models.Collaborator;
import com.example.exam.models.Repository;

import java.util.ArrayList;
import java.util.List;

public class CollaboratorDTO {
    private long id;
	private String login;
	private String password;

	private List<Long> repositories;

	//Constructor
    public CollaboratorDTO(){}
    public CollaboratorDTO(Collaborator collaborator){
        this.id = collaborator.getId();
		this.login = collaborator.getLogin();
		this.password = collaborator.getPassword();

		if (collaborator.getRepositories() != null){
			repositories = collaborator.getRepositories().stream().map(Repository::getId).toList();
		}
		else {
			repositories = new ArrayList<>();
		}
    }

    //Get
    public long getId() {return id;}
	public String getLogin(){return login;}
	public String getPassword(){
		return password;
	}

	public List<Long> getRepositories() {
		return repositories;
	}

	//Set
	public void setLogin(String login){this.login = login;}
	public void setPassword(String password){this.password = password;}

	public void setRepositories(List<Long> repositories){
		this.repositories = repositories;
	}
}