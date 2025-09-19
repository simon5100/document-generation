package com.simon.documentgeneration.service;

import com.simon.documentgeneration.dto.DocumentJobRegulationRequest;
import org.springframework.http.HttpHeaders;

import java.util.HashMap;

public interface DocumentGenerationService {

    HashMap<HttpHeaders, byte[]> generatedDocument(DocumentJobRegulationRequest request);
}
