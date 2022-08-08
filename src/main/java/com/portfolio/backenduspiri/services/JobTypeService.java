package com.portfolio.backenduspiri.services;

import com.portfolio.backenduspiri.model.JobType;
import com.portfolio.backenduspiri.repository.JobTypeRepository;
import com.portfolio.backenduspiri.service_interface.IJobTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobTypeService implements IJobTypeService {
    
    @Autowired
    public JobTypeRepository jobRepo;

    @Override
    public List<JobType> getJobTypes() {
        return jobRepo.findAll();
    }

    @Override
    public void createJobType(JobType jobType) {
        jobRepo.save(jobType);
    }

    @Override
    public void deleteJobType(Long id) {
        jobRepo.deleteById(id);
    }

    @Override
    public JobType getJobType(Long id) {
        return jobRepo.findById(id).orElse(null);
    }

    @Override
    public JobType updateJobType(JobType jobType) {
        jobRepo.save(jobType);
        return jobType;
    }
    
}
