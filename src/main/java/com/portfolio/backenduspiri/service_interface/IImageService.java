package com.portfolio.backenduspiri.service_interface;

import com.portfolio.backenduspiri.model.Image;
import java.util.List;

public interface IImageService {
    
    public List<Image> getImages();
    
    public void createImage( Image img );
    
    public void deleteImage( Long id );
    
    public Image getImage( Long id );
    
    public Image findByPersonId( Long id );
    
    public Image updateImage( Image img );
    
}
