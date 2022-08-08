package com.portfolio.backenduspiri.services;

import com.portfolio.backenduspiri.model.Image;
import com.portfolio.backenduspiri.repository.ImageRepository;
import com.portfolio.backenduspiri.service_interface.IImageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService implements IImageService {
    
    @Autowired
    public ImageRepository imgRepo;

    @Override
    public List<Image> getImages() {
        return imgRepo.findAll();
    }

    @Override
    public void createImage(Image img) {
        imgRepo.save(img);
    }

    @Override
    public void deleteImage(Long id) {
        imgRepo.deleteById(id);
    }

    @Override
    public Image getImage(Long id) {
        return imgRepo.findById(id).orElse(null);
    }

    @Override
    public Image findByPersonId(Long id) {
        return imgRepo.findByPersonId(id);
    }

    @Override
    public Image updateImage(Image img) {
        imgRepo.save(img);
        return img;
    }
    
}
