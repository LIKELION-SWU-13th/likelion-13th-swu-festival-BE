package com.example.likelion_13th_festival_BE.controller;

import com.example.likelion_13th_festival_BE.service.NaverOCRService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/ocr")
@RequiredArgsConstructor
public class NaverOCRController {
    private final NaverOCRService ocrService;

    /**
     * POST /api/ocr/general
     * Content-Type: multipart/form-data
     * form-data:
     *   - file: (이미지 파일)
     * => 네이버 General OCR 결과 전체 JSON 을 리턴
     */
    @PostMapping(value = "/general", consumes = "multipart/form-data")
    public ResponseEntity<JsonNode> generalOcr(@RequestParam("file") MultipartFile file) throws Exception {
        JsonNode ocrResult = ocrService.recognize(file);
        return ResponseEntity.ok(ocrResult);
    }
}
