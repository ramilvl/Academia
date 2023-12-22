package com.project.OnlineLearning.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="role")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
