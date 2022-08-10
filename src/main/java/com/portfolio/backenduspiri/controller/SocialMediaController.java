package com.portfolio.backenduspiri.controller;

import com.portfolio.backenduspiri.model.SocialMedia;
import com.portfolio.backenduspiri.service_interface.IPersonService;
import com.portfolio.backenduspiri.service_interface.ISocialMediaService;
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
@RequestMapping("/social")
@CrossOrigin( origins = {"https://uriel-spiridione.web.app/","http://localhost:4200/"} )
public class SocialMediaController {
    
    @Autowired
    private ISocialMediaService socialService;
    
    @Autowired
    private IPersonService personService;
    
    @GetMapping
    @ResponseBody
    public List<SocialMedia> getSocialMedia(){
        return socialService.getSocialMedias();
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public SocialMedia getSocialMedia( @PathVariable Long id ){
        return socialService.getSocialMedia( id );
    }
    
    @GetMapping("person/{id}")
    public SocialMedia getSocialMediaByPersonId( @PathVariable Long id ){
        return socialService.findByPersonId(id);
    }
    
    @PostMapping("/{id}")
    public void saveSocialMedia( @PathVariable Long id, @RequestBody SocialMedia social ){
        social.setPerson(personService.getPerson(id));
        socialService.createSocialMedia(social);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public SocialMedia updateSocialMedia( @PathVariable Long id, @RequestBody SocialMedia social ){

        SocialMedia socialToUpdate = socialService.getSocialMedia(id);
        
        socialToUpdate.setFacebook(social.getFacebook());
        socialToUpdate.setTwitter(social.getTwitter());
        socialToUpdate.setInstagram(social.getInstagram());
        socialToUpdate.setGithub(social.getGithub());
        socialToUpdate.setLinkedin(social.getLinkedin());
        socialToUpdate.setCv(social.getCv());
        
        return socialService.updateSocialMedia(socialToUpdate);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteSocialMedia( @PathVariable Long id ){
        socialService.deleteSocialMedia(id);
    }
    
}
