package com.project.OnlineLearning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content; // The actual question text

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    private String correctAnswer; // The correct option

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

}
