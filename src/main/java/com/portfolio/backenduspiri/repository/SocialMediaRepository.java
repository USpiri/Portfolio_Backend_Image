package com.portfolio.backenduspiri.repository;

import com.portfolio.backenduspiri.model.SocialMedia;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialMediaRepository extends JpaRepository<SocialMedia, Long> {
    
    public SocialMedia findByPersonId( Long id );
    
}
