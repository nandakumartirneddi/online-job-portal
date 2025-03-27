package com.portal.controller;


import com.portal.service.JobService;
import com.portal.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    // 🔹 Post a new job - Admin Only
    @PostMapping("/post")
    public ResponseEntity<Job> postJob(@RequestBody Job job, @AuthenticationPrincipal UserDetails userDetails) {
        job.setPostedBy(userDetails.getUsername()); // Set email from token
        return ResponseEntity.ok(jobService.postJob(job));
    }
    // 🔹 Public: View all jobs
    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

}
