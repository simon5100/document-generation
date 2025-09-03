package com.simon.documentgeneration.service;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.IOException;

public interface DocumentGenerationService {

    PDDocument generatedDocument1() throws IOException;

    byte[] generatedDocument2();
}
