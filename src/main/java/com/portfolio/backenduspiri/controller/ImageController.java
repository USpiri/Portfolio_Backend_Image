package com.portfolio.backenduspiri.controller;

import com.portfolio.backenduspiri.model.Image;
import com.portfolio.backenduspiri.service_interface.IImageService;
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
@RequestMapping("/image")
@CrossOrigin( origins = "https://uriel-spiridione.web.app/" )
public class ImageController {
    
    @Autowired
    private IImageService imgService;
    
    @Autowired
    private IPersonService personService;
    
    @Autowired
    CloudinaryService cloudinaryService;
    
    @GetMapping
    @ResponseBody
    public List<Image> getImage(){
        return imgService.getImages();
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public Image getImage( @PathVariable Long id ){
        return imgService.getImage( id );
    }
    
    @GetMapping("person/{id}")
    public Image getImageByPersonId( @PathVariable Long id ){
        return imgService.findByPersonId(id);
    }
    
    @PostMapping("/{id}")
    public void saveImage( @PathVariable Long id, @RequestBody Image img ){
        img.setPerson(personService.getPerson(id));
        imgService.createImage(img);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}") //SAVE TWO IMAGES
    public Image updateImage( @PathVariable Long id, @RequestParam("image") MultipartFile[] image ) throws IOException{
        Image imgToUpdate = imgService.getImage(id);
        
        Map resultH = cloudinaryService.upload(image[0]);
        imgToUpdate.setHeader(resultH.get("url").toString());
        imgToUpdate.setHeader_id(resultH.get("public_id").toString());
        
        Map resultA = cloudinaryService.upload(image[1]);
        imgToUpdate.setAbout(resultA.get("url").toString());
        imgToUpdate.setAbout_id(resultA.get("public_id").toString());
        
        return imgService.updateImage(imgToUpdate);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/header") //SAVE Header IMAGE
    public Image updateHeaderImage( @PathVariable Long id, @RequestParam("image") MultipartFile image ) throws IOException{
        Image imgToUpdate = imgService.getImage(id);
        
        Map resultH = cloudinaryService.upload(image);
        imgToUpdate.setHeader(resultH.get("url").toString());
        imgToUpdate.setHeader_id(resultH.get("public_id").toString());
        
        return imgService.updateImage(imgToUpdate);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/about") //SAVE About IMAGE
    public Image updateAboutImage( @PathVariable Long id, @RequestParam("image") MultipartFile image ) throws IOException{
        Image imgToUpdate = imgService.getImage(id);
        
        Map resultA = cloudinaryService.upload(image);
        imgToUpdate.setAbout(resultA.get("url").toString());
        imgToUpdate.setAbout_id(resultA.get("public_id").toString());
        
        return imgService.updateImage(imgToUpdate);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteImage( @PathVariable Long id ) throws IOException{
        
        Map resultH = cloudinaryService.delete(imgService.getImage(id).getHeader_id());
        Map resultA = cloudinaryService.delete(imgService.getImage(id).getAbout_id());
        
        imgService.deleteImage(id);
    }
    
}
