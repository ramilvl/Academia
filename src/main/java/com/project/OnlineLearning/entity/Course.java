package com.project.OnlineLearning.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;


import java.util.Set;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;
    
    
    @ManyToMany(mappedBy = "courses")
    private Set<User> students;

    @OneToMany(mappedBy = "course")
    private Set<Enrollment> enrollments;

    
}
