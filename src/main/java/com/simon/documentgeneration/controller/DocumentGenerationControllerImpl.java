package com.simon.documentgeneration.controller;

import com.simon.documentgeneration.dto.DocumentJobRegulationRequest;
import com.simon.documentgeneration.service.DocumentGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class DocumentGenerationControllerImpl implements DocumentGenerationController {

    private final DocumentGenerationService documentGenerationService;

    @Override
    public ResponseEntity<byte[]> downlandFile(DocumentJobRegulationRequest request) {

        HashMap<HttpHeaders, byte[]> documentContainer = documentGenerationService.generatedDocument(request);

        HttpHeaders headers = documentContainer.keySet().stream().findFirst().get();

        byte[] document = documentContainer.get(documentContainer.keySet().stream().findFirst().get());

        return new ResponseEntity<>(document, headers, HttpStatus.OK);
    }
}