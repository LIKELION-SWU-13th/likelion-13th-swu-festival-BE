package com.example.likelion_13th_festival_BE.apiPayload.exception.handler;

import com.example.likelion_13th_festival_BE.apiPayload.code.BaseErrorCode;
import com.example.likelion_13th_festival_BE.apiPayload.exception.GeneralException;

public class EventHandler extends GeneralException {
    public EventHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
