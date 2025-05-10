package com.example.likelion_13th_festival_BE.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NaverOCRService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${naver.ocr.general.url}")
    private String ocrUrl;

    @Value("${naver.ocr.secret}")
    private String ocrSecret;

    /*
     * MultipartFile 을 받아서 General OCR API 호출 후 응답 전체를 JsonNode 로 리턴
     */
    public JsonNode recognize(MultipartFile file) throws Exception {
        // 1) message JSON 만들기
        Map<String, Object> message = Map.of(
                "version",   "V2",
                "requestId", UUID.randomUUID().toString(),
                "timestamp", System.currentTimeMillis(),
                "lang",      "ko",
                "images", List.of(
                        Map.of(
                                "format", extractExtension(file.getOriginalFilename()),
                                "name",   "document"
                        )
                )
        );
        String messageJson = objectMapper.writeValueAsString(message);

        // 2) multipart body
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        // — message part (JSON)
        HttpHeaders jsonHeaders = new HttpHeaders();
        jsonHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> jsonPart = new HttpEntity<>(messageJson, jsonHeaders);
        body.add("message", jsonPart);

        // — file part (bytes)
        ByteArrayResource imagePart = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };
        body.add("file", imagePart);

        // 3) 전체 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("X-OCR-SECRET", ocrSecret);

        HttpEntity<MultiValueMap<String,Object>> requestEntity =
                new HttpEntity<>(body, headers);

        // 4) 요청 전송
        ResponseEntity<String> response =
                restTemplate.postForEntity(ocrUrl, requestEntity, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("OCR API 호출 실패: " + response.getStatusCode());
        }

        // 5) 응답 JSON 파싱
        return objectMapper.readTree(response.getBody());
    }

    /* 파일명에서 확장자(jpg, png)만 추출 */
    private String extractExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return "jpg";
        }
        return filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
    }
}
