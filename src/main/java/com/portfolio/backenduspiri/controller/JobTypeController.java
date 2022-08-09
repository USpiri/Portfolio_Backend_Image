package com.portfolio.backenduspiri.controller;

import com.portfolio.backenduspiri.model.JobType;
import com.portfolio.backenduspiri.service_interface.IJobTypeService;
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
@RequestMapping("/jobtype")
@CrossOrigin( origins = "https://uriel-spiridione.web.app/" )
public class JobTypeController {
    
    @Autowired
    private IJobTypeService jobService;
    
    @GetMapping
    @ResponseBody
    public List<JobType> getJobType(){
        return jobService.getJobTypes();
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public JobType getJobType( @PathVariable Long id ){
        return jobService.getJobType( id );
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public JobType saveJobType( @RequestBody JobType job ){
        jobService.createJobType(job);
        return jobService.getJobType(job.getId());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public JobType updateJobType( @PathVariable Long id, @RequestBody JobType job ){
        JobType jobToUpdate = jobService.getJobType(id);
        
        jobToUpdate.setName(job.getName());
        
        return jobService.updateJobType(jobToUpdate);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteJobType( @PathVariable Long id ){
        jobService.deleteJobType(id);
    }
    
}
