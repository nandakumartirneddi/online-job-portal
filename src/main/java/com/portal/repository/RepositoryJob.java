package com.portal.repository;

import com.portal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryJob extends JpaRepository<Job, Long> {
    
}
