package com.example.likelion_13th_festival_BE.apiPayload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.example.likelion_13th_festival_BE.apiPayload.code.BaseErrorCode;
import com.example.likelion_13th_festival_BE.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}
