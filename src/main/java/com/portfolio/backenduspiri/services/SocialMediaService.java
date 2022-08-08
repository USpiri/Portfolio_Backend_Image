package com.portfolio.backenduspiri.services;

import com.portfolio.backenduspiri.model.SocialMedia;
import com.portfolio.backenduspiri.repository.SocialMediaRepository;
import com.portfolio.backenduspiri.service_interface.ISocialMediaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialMediaService implements ISocialMediaService {
    
    @Autowired
    public SocialMediaRepository socialRepo;

    @Override
    public List<SocialMedia> getSocialMedias() {
        return socialRepo.findAll();
    }

    @Override
    public void createSocialMedia(SocialMedia social) {
        socialRepo.save(social);
    }

    @Override
    public void deleteSocialMedia(Long id) {
        socialRepo.deleteById(id);
    }

    @Override
    public SocialMedia getSocialMedia(Long id) {
        return socialRepo.findById(id).orElse(null);
    }

    @Override
    public SocialMedia findByPersonId(Long id) {
        return socialRepo.findByPersonId(id);
    }

    @Override
    public SocialMedia updateSocialMedia(SocialMedia social) {
        socialRepo.save(social);
        return social;
    }
    
}
