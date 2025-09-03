package com.simon.documentgeneration.controller;

import com.itextpdf.text.Document;
import com.simon.documentgeneration.service.DocumentGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class DocumentGenerationControllerImpl implements DocumentGenerationController {

    private final DocumentGenerationService documentGenerationService;

    @Override
    public ResponseEntity<byte[]> downlandFile() {

        byte[] bytes = documentGenerationService.generatedDocument2();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(
                ContentDisposition.attachment()
                .filename("document.pdf")
                .build());
        headers.setContentLength(bytes.length);

        return new ResponseEntity<>(bytes, headers,HttpStatus.OK);
    }
}
