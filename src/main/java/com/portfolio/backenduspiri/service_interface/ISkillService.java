package com.portfolio.backenduspiri.service_interface;

import com.portfolio.backenduspiri.model.Skill;
import java.util.List;

public interface ISkillService {
    
    public List<Skill> getSkills();
    
    public void createSkill( Skill skill );
    
    public void deleteSkill( Long id );
    
    public Skill getSkill( Long id );
    
    public List<Skill> findByPersonId( Long id );
    
    public Skill updateSkill( Skill skill );
    
}
