package com.portal.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "jobs")
@NoArgsConstructor @AllArgsConstructor
@Builder
@Data
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 🔹 Auto-generates job ID
    private Long id;

    private String title;
    private String description;
    private String location;
    private String type;
    private String company;
    private String url;
    private String skills;
    private String salary;
    private String contact;
    private String postedOn;
    private String deadline;


    @Column(name = "Posted_By")
    private String postedBy; 
}
