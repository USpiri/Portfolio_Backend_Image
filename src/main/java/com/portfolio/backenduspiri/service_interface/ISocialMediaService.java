package com.portfolio.backenduspiri.service_interface;

import com.portfolio.backenduspiri.model.SocialMedia;
import java.util.List;

public interface ISocialMediaService {
    
    public List<SocialMedia> getSocialMedias();
    
    public void createSocialMedia( SocialMedia social );
    
    public void deleteSocialMedia( Long id );
    
    public SocialMedia getSocialMedia( Long id );
    
    public SocialMedia findByPersonId( Long id );
    
    public SocialMedia updateSocialMedia( SocialMedia social );
    
}
