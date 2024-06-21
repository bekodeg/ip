package com.example.exam.services;

import com.example.exam.models.Issue;
import com.example.exam.repositories.IssueRepository;
import com.example.exam.core.utils.validation.ValidatorUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.FindException;
import java.util.List;

@Service
public class IssueService {
    private final IssueRepository repository;
    private final ValidatorUtil validatorUtil;

    public IssueService(IssueRepository repository, ValidatorUtil validatorUtil) {
        this.repository = repository;
        this.validatorUtil = validatorUtil;
    }

    //Methods
    @Transactional
    public Issue add(Issue issue){
        validatorUtil.validate(issue);
        return repository.save(issue);
    }

    @Transactional
    public Issue find(Long id){
        return repository.findById(id)
                .orElseThrow(()->new FindException("Issue with id "+id+" do not find"));
    }

    @Transactional
    public List<Issue> getAll(){
        return repository.findAll();
    }

    @Transactional
    public Issue update(Issue issue){
        Issue exist = find(issue.getId());
        exist.setName(issue.getName());
        exist.setMilestone(issue.getMilestone());
        exist.setCompleted(issue.isCompleted());

        validatorUtil.validate(exist);
        return repository.save(exist);
    }
    @Transactional
    public Issue delete(Long id){
        Issue exist = find(id);
        repository.delete(exist);
        return exist;
    }

    @Transactional
    public void deleteAll(){
        repository.deleteAll();
    }
}
