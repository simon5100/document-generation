package com.simon.documentgeneration.controller;

import com.simon.documentgeneration.dto.DocumentJobRegulationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/documents")
public interface DocumentGenerationController {

    @PostMapping("/v1/generation/jobRegulation/secretarySession")
    ResponseEntity<byte[]> downlandFile(@RequestBody DocumentJobRegulationRequest request);
}
