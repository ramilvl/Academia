package com.project.OnlineLearning.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

// Course.java in the entity package
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;

    // Other fields and annotations...

    @ManyToMany(mappedBy = "courses")
    private Set<User> students;

    @OneToMany(mappedBy = "course")
    private Set<Enrollment> enrollments;

    // Getter and setter methods...

    // Add the following getter method for students
    public Set<User> getStudents() {
        return students;
    }
}
