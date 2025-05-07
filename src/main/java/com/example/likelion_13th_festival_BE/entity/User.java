package com.example.likelion_13th_festival_BE.entity;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String student_num;

    private String name;

    private String major;

    private String password;
}
