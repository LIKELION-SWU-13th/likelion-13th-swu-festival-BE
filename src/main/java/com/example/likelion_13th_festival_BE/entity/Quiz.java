package com.example.likelion_13th_festival_BE.entity;

import com.example.likelion_13th_festival_BE.constant.QuizStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Quiz {
    @Id
    @Column(name = "quiz_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    private String a_body;

    private String b_body;

    @Enumerated(EnumType.STRING)
    private QuizStatus quizStatus;

    private LocalDateTime open_time;

}
