package com.portfolio.backenduspiri.controller;

import com.portfolio.backenduspiri.model.Image;
import com.portfolio.backenduspiri.service_interface.IImageService;
import com.portfolio.backenduspiri.service_interface.IPersonService;
import com.portfolio.backenduspiri.util.FileUploadUtil;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
        
        String apiURL = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/";
        String uploadDir = "user-photos/" + imgToUpdate.getPerson().getId();//
        String header = "header.jpg";
        String about = "about.jpg";
        
        //Updates Image Object
        imgToUpdate.setHeader(apiURL + uploadDir + "/" + header);
        imgToUpdate.setAbout(apiURL + uploadDir + "/" + about);
        
        //Save images in folders
        FileUploadUtil.saveFile(uploadDir, header, image[0]);
        FileUploadUtil.saveFile(uploadDir, about, image[1]);
        
        return imgService.updateImage(imgToUpdate);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/header") //SAVE Header IMAGE
    public Image updateHeaderImage( @PathVariable Long id, @RequestParam("image") MultipartFile image ) throws IOException{
        Image imgToUpdate = imgService.getImage(id);
        
        String apiURL = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/";
        String uploadDir = "user-photos/" + imgToUpdate.getPerson().getId();//
        String header = "header.jpg";
        
        //Updates Image Object
        imgToUpdate.setHeader(apiURL + uploadDir + "/" + header);
        
        //Save images in folders
        FileUploadUtil.saveFile(uploadDir, header, image);
        
        return imgService.updateImage(imgToUpdate);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/about") //SAVE About IMAGE
    public Image updateAboutImage( @PathVariable Long id, @RequestParam("image") MultipartFile image ) throws IOException{
        Image imgToUpdate = imgService.getImage(id);
        
        String apiURL = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/";
        String uploadDir = "user-photos/" + imgToUpdate.getPerson().getId();
        String about = "about.jpg";
        
        //Updates Image Object
        imgToUpdate.setAbout(apiURL + uploadDir + "/" + about);
        
        //Save images in folders
        FileUploadUtil.saveFile(uploadDir, about, image);
        
        return imgService.updateImage(imgToUpdate);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteImage( @PathVariable Long id ){
        imgService.deleteImage(id);
    }
    
}
