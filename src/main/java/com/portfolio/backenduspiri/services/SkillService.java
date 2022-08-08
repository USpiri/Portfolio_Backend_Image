package com.portfolio.backenduspiri.services;

import com.portfolio.backenduspiri.model.Skill;
import com.portfolio.backenduspiri.repository.SkillRepository;
import com.portfolio.backenduspiri.service_interface.ISkillService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService implements ISkillService {
    
    @Autowired
    public SkillRepository skillRepo;

    @Override
    public List<Skill> getSkills() {
        return skillRepo.findAll();
    }

    @Override
    public void createSkill(Skill skill) {
        skillRepo.save(skill);
    }

    @Override
    public void deleteSkill(Long id) {
        skillRepo.deleteById(id);
    }

    @Override
    public Skill getSkill(Long id) {
        return skillRepo.findById(id).orElse(null);
    }

    @Override
    public List<Skill> findByPersonId(Long id) {
        return skillRepo.findByPersonId(id);
    }

    @Override
    public Skill updateSkill(Skill skill) {
        skillRepo.save(skill);
        return skill;
    }
    
}
