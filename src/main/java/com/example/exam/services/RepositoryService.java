package com.example.exam.services;

import com.example.exam.models.Repository;
import com.example.exam.repositories.RepositoryRepository;
import com.example.exam.core.utils.validation.ValidatorUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.FindException;
import java.util.List;

@Service
public class RepositoryService {
    private final RepositoryRepository repositoryR;
    private final ValidatorUtil validatorUtil;

    //Constructor
    public RepositoryService(RepositoryRepository repositoryR,
                             ValidatorUtil validatorUtil){
        this.repositoryR = repositoryR;
        this.validatorUtil = validatorUtil;
    }
    //Methods
    @Transactional
    public Repository add(Repository repository){
        validatorUtil.validate(repository);
        return repositoryR.save(repository);
    }
    @Transactional
    public Repository find(Long id){
        return repositoryR.findById(id)
                .orElseThrow(()->new FindException("Repository with id "+id+" do not find"));
    }

    @Transactional
    public List<Repository> getAll(){
        return repositoryR.findAll();
    }

    @Transactional
    public Page<Repository> getAll(int page, int size){
        final Pageable pageRequest = PageRequest.of(page, size, Sort.by("id"));
        return repositoryR.findAll(pageRequest);
    }

    @Transactional
    public Page<Repository> getAll(Long collaboratorId, int page, int size){
        final Pageable pageRequest = PageRequest.of(page, size, Sort.by("id"));
        return repositoryR.findAllByCollaboratorId(collaboratorId, pageRequest);
    }

    @Transactional
    public Repository update(Repository repository){
        Repository exist = find(repository.getId());
        exist.setName(repository.getName());
        exist.setCollaborator(repository.getCollaborator());
        exist.setMilestones(repository.getMilestones());

		validatorUtil.validate(exist);
        return repositoryR.save(exist);
    }
    @Transactional
    public Repository delete(Long id){
        Repository exist = find(id);
        repositoryR.delete(exist);
        return exist;
    }
    @Transactional
    public void deleteAll(){
        repositoryR.deleteAll();
    }
}