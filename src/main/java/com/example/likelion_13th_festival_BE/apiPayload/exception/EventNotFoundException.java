package com.example.likelion_13th_festival_BE.apiPayload.exception;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(String message) {
        super(message);
    }
}
