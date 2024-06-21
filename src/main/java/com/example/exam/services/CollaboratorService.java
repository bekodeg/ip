package com.example.exam.services;

import com.example.exam.models.Collaborator;
import com.example.exam.repositories.CollaboratorRepository;
import com.example.exam.core.utils.validation.ValidatorUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.FindException;
import java.util.List;

@Service
public class CollaboratorService {
    private final CollaboratorRepository repository;
    private final ValidatorUtil validatorUtil;
    //Constructor
    public CollaboratorService(CollaboratorRepository repository,
                               ValidatorUtil validatorUtil){
        this.repository = repository;
        this.validatorUtil = validatorUtil;
    }

    //Methods
    @Transactional
    public Collaborator add(Collaborator collaborator){
        validatorUtil.validate(collaborator);
        return repository.save(collaborator);
    }

    @Transactional
    public Collaborator find(String login, String password){
        return repository.findByLoginAndPassword(login, password)
                .orElseThrow(()->new FindException("Collaborator with login " + login + " and password " + password + " do not find"));
    }

    @Transactional
    public Collaborator find(Long id){
        return repository.findById(id)
                .orElseThrow(()->new FindException("Collaborator with id "+id+" do not find"));
    }

    @Transactional
    public List<Collaborator> getAll(){
        return repository.findAll();
    }

    @Transactional
    public Collaborator update(Collaborator collaborator){
        Collaborator exist = find(collaborator.getId());
        exist.setLogin(collaborator.getLogin());
        exist.setPassword(collaborator.getPassword());

        exist.setRepositories(collaborator.getRepositories());
        exist.setRole(collaborator.getRole());

		validatorUtil.validate(exist);
        return repository.save(exist);
    }
    @Transactional
    public Collaborator delete(Long id){
        Collaborator collaborator = find(id);
        repository.delete(collaborator);
        return collaborator;
    }

    @Transactional
    public void deleteAll(){
        repository.deleteAll();
    }
}