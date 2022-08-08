package com.portfolio.backenduspiri.service_interface;

import com.portfolio.backenduspiri.model.JobType;
import java.util.List;

public interface IJobTypeService {
    
    public List<JobType> getJobTypes();
    
    public void createJobType(JobType jobType);
    
    public void deleteJobType( Long id );
    
    public JobType getJobType( Long id );
    
    public JobType updateJobType( JobType jobType );
    
}
