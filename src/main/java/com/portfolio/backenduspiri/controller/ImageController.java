package com.portfolio.backenduspiri.controller;

import com.portfolio.backenduspiri.model.Image;
import com.portfolio.backenduspiri.service_interface.IImageService;
import com.portfolio.backenduspiri.service_interface.IPersonService;
import java.io.IOException;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;
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
@CrossOrigin( origins = "https://uriel-spiridione.web.app" )
public class ImageController {
    
    @Autowired
    private IImageService imgService;
    
    @Autowired
    private IPersonService personService;
    
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
        
        try {
            byte[] headerB = Base64.encodeBase64(image[0].getBytes());
            String header = new String(headerB);
            byte[] aboutB = Base64.encodeBase64(image[1].getBytes());
            String about = new String(aboutB);
            imgToUpdate.setHeader(header);
            imgToUpdate.setAbout(about);
        } catch (IOException e) {
            System.out.println(e);
        }
        
        return imgService.updateImage(imgToUpdate);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/header") //SAVE Header IMAGE
    public Image updateHeaderImage( @PathVariable Long id, @RequestParam("image") MultipartFile image ) throws IOException{
        Image imgToUpdate = imgService.getImage(id);
        
        try {
            byte[] headerB = Base64.encodeBase64(image.getBytes());
            String header = new String(headerB);
            imgToUpdate.setHeader(header);
        } catch (IOException e) {
            System.out.println(e);
        }
        
        return imgService.updateImage(imgToUpdate);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/about") //SAVE About IMAGE
    public Image updateAboutImage( @PathVariable Long id, @RequestParam("image") MultipartFile image ) throws IOException{
        Image imgToUpdate = imgService.getImage(id);
        
        try {
            byte[] aboutB = Base64.encodeBase64(image.getBytes());
            String about = new String(aboutB);
            imgToUpdate.setAbout(about);
        } catch (IOException e) {
            System.out.println(e);
        }
        
        return imgService.updateImage(imgToUpdate);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteImage( @PathVariable Long id ){
        imgService.deleteImage(id);
    }
    
}
