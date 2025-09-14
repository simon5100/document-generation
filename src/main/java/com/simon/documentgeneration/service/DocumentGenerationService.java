package com.simon.documentgeneration.service;

import com.simon.documentgeneration.dto.DocumentJobRegulationRequest;

public interface DocumentGenerationService {

    byte[] generatedDocument(DocumentJobRegulationRequest request);
}
