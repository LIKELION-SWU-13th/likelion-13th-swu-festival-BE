package com.example.likelion_13th_festival_BE.controller;

import com.example.likelion_13th_festival_BE.apiPayload.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
    @GetMapping("/test")
    public ResponseEntity<String> TestController() {
        return ResponseEntity.ok("1");
    }
}
