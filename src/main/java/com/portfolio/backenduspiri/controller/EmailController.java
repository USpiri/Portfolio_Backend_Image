package com.portfolio.backenduspiri.controller;

import com.portfolio.backenduspiri.model.Email;
import com.portfolio.backenduspiri.service_interface.IEmailSender;
import com.portfolio.backenduspiri.service_interface.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin( origins = "http://localhost:4200/" )
public class EmailController {
    
    @Autowired
    private IEmailSender sender;
    
    @Autowired
    private IPersonService personService;
    
    @PostMapping("/{person_id}/send-email")
    public ResponseEntity sendEmail( @PathVariable Long person_id,@RequestBody Email email){
        email.setTo(personService.getPerson(person_id).getEmail());
        this.sender.sendEmail( email.getTo(), email.getSubject(), email.getMessage() );
        return ResponseEntity.ok("Success");
    }
    
}
