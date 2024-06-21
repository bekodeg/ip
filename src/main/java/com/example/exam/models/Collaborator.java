package com.example.exam.models;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Collaborator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	@Column(nullable = false,  unique = false)
	private String login;
	@Column(nullable = false,  unique = false)
	private String password;

    @OneToMany(mappedBy = "collaborator")
    private Set<Repository> repositories = new HashSet<>();

    private UserRole role;

    //Constructor

    public Collaborator(){}
	public Collaborator(String login, String password){
		this.login = login;
		this.password = password;
        this.role = UserRole.USER;
	}
	//Override
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collaborator collaborator = (Collaborator) o;
        return Objects.equals(id, collaborator.id);
    }

    @Override
    public int hashCode() {return Objects.hash(id);}
    //Get
    public long getId() {
        return id;
    }
	public String getLogin(){
        return login;
    }
	public String getPassword(){
        return password;
    }

    public Set<Repository> getRepositories(){
        return repositories;
    }

    public UserRole getRole(){
        return role;
    }

	//Set
	public void setLogin(String login){
        this.login = login;
    }
	public void setPassword(String password){
        this.password = password;
    }

    public void setRepositories(Set<Repository> repositories){
        this.repositories = repositories;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}