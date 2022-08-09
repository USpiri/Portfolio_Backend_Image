package com.portfolio.backenduspiri.controller;

import com.portfolio.backenduspiri.model.Education;
import com.portfolio.backenduspiri.service_interface.IEducationService;
import com.portfolio.backenduspiri.service_interface.IPersonService;
import com.portfolio.backenduspiri.services.CloudinaryService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/education")
@CrossOrigin( origins = "https://uriel-spiridione.web.app/" )
public class EducationController {
    
    @Autowired
    private IEducationService educationService;
    
    @Autowired
    private IPersonService personService;
    
    @Autowired
    CloudinaryService cloudinaryService;
    
    @GetMapping
    @ResponseBody
    public List<Education> getEducation(){
        return educationService.getEducation();
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public Education getEducation( @PathVariable Long id ){
        return educationService.getEducation( id );
    }
    
    @GetMapping("person/{id}")
    public List<Education> getEducationByPersonId( @PathVariable Long id ){
        return educationService.findByPersonId(id);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}")
    public Education saveEducation( @PathVariable Long id, @RequestBody Education edu ){
        edu.setPerson(personService.getPerson(id));
        educationService.createEducation(edu);
        return educationService.getEducation(edu.getId());
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Education updateEducation( @PathVariable Long id, @RequestBody Education edu ){
        Education eduToUpdate = educationService.getEducation(id);
        
        eduToUpdate.setWhere(edu.getWhere());
        eduToUpdate.setDegree(edu.getDegree());
        eduToUpdate.setIs_actual(edu.getIs_actual());
        eduToUpdate.setStart_date(edu.getStart_date());
        eduToUpdate.setEnd_date(edu.getEnd_date());
        eduToUpdate.setImg_url(edu.getImg_url());
        eduToUpdate.setLink(edu.getLink());
        eduToUpdate.setEnabled_link(edu.getEnabled_link());
        
        return educationService.updateEducation(eduToUpdate);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/image")
    public Education updateEducationImage( @PathVariable Long id, @RequestParam("education") MultipartFile edu ) throws IOException{
        Education eduToUpdate = educationService.getEducation(id);
        
        Map result = cloudinaryService.upload(edu);
        eduToUpdate.setImg_url(result.get("url").toString());
        eduToUpdate.setImageId(result.get("public_id").toString());
        
        return educationService.updateEducation(eduToUpdate);
        
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteEducation( @PathVariable Long id ) throws IOException{
        Map result = cloudinaryService.delete(educationService.getEducation(id).getImageId());
        educationService.deleteEducation(id);
    }
    
    
}
