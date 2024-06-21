package com.example.exam.services;

import com.example.exam.models.Milestone;
import com.example.exam.repositories.MilestoneRepository;
import com.example.exam.core.utils.validation.ValidatorUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.FindException;
import java.util.List;

@Service
public class MilestoneService {
    private final MilestoneRepository repository;
    private final ValidatorUtil validatorUtil;

    public MilestoneService(MilestoneRepository repository, ValidatorUtil validatorUtil) {
        this.repository = repository;
        this.validatorUtil = validatorUtil;
    }

    //Methods
    @Transactional
    public Milestone add(Milestone milestone){
        validatorUtil.validate(milestone);
        return repository.save(milestone);
    }

    @Transactional
    public Milestone find(Long id){
        return repository.findById(id)
                .orElseThrow(()->new FindException("Milestone with id "+id+" do not find"));
    }

    @Transactional
    public List<Milestone> getAll(){
        return repository.findAll();
    }

    @Transactional
    public Milestone update(Milestone milestone){
        Milestone exist = find(milestone.getId());
        exist.setName(milestone.getName());
        exist.setIssues(milestone.getIssues());
        exist.setRepository(milestone.getRepository());

        validatorUtil.validate(exist);
        return repository.save(exist);
    }
    @Transactional
    public Milestone delete(Long id){
        Milestone exist = find(id);
        repository.delete(exist);
        return exist;
    }

    @Transactional
    public void deleteAll(){
        repository.deleteAll();
    }
}
