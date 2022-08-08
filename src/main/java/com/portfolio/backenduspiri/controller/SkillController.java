package com.portfolio.backenduspiri.controller;

import com.portfolio.backenduspiri.model.Skill;
import com.portfolio.backenduspiri.service_interface.IPersonService;
import com.portfolio.backenduspiri.service_interface.ISkillService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skill")
@CrossOrigin( origins = "https://uriel-spiridione.web.app" )
public class SkillController {
    
    @Autowired
    private ISkillService skillService;
    
    @Autowired
    private IPersonService personService;
    
    @GetMapping
    @ResponseBody
    public List<Skill> getSkill(){
        return skillService.getSkills();
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public Skill getSkill( @PathVariable Long id ){
        return skillService.getSkill( id );
    }
    
    @GetMapping("person/{id}")
    public List<Skill> getSkillByPersonId( @PathVariable Long id ){
        return skillService.findByPersonId(id);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}")
    public Skill saveSkill( @PathVariable Long id, @RequestBody Skill skill ){
        skill.setPerson(personService.getPerson(id));
        skillService.createSkill(skill);
        return skillService.getSkill(skill.getId());
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Skill updateSkill( @PathVariable Long id, @RequestBody Skill skill ){
        Skill skillToUpdate = skillService.getSkill(id);
        
        skillToUpdate.setName(skill.getName());
        skillToUpdate.setPercentage(skill.getPercentage());
        skillToUpdate.setIcon(skill.getIcon());
        
        return skillService.updateSkill(skillToUpdate);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteSkill( @PathVariable Long id ){
        skillService.deleteSkill(id);
    }
    
}
