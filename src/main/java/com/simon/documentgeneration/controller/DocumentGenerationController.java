package com.simon.documentgeneration.controller;

import com.itextpdf.text.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/documents")
public interface DocumentGenerationController {

    @GetMapping("/v1/downland")
    ResponseEntity<byte[]> downlandFile();
}
