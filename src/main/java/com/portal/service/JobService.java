package com.portal.service;

import com.portal.entity.Job;
import org.springframework.stereotype.Service;
import com.portal.repository.RepositoryJob;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private RepositoryJob repositoryJob;

    public Job postJob(Job job) {
        return repositoryJob.save(job);
    }
    
    // 🔹 Fetches all rows from the jobs table
    public List<Job> getAllJobs() {
        return repositoryJob.findAll();
    }
    
}
