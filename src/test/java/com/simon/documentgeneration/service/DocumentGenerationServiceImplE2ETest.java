package com.simon.documentgeneration.service;

import com.simon.documentgeneration.dto.DocumentJobRegulationRequest;
import com.simon.documentgeneration.exception.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("E2E тесты методов DocumentGenerationServiceImpl")
public class DocumentGenerationServiceImplE2ETest {

    @Autowired
    private TestRestTemplate restTemplate;

    private DocumentJobRegulationRequest request;

    @LocalServerPort  // Добавьте эту аннотацию
    private int port;

    private String url = "http://localhost:8081/api/documents/v1/generation/jobRegulation/secretarySession";

    private ResponseEntity<byte[]> responseEntity;

    private ResponseEntity<ErrorResponse> responseError;

    @BeforeEach
    void setUp(){

        url = "http://localhost:" + port + "/api/documents/v1/generation/jobRegulation/secretarySession";

        responseEntity = null;
        responseError = null;

        request = new DocumentJobRegulationRequest(
                "Центральный",
                1,
                "И.И. Иванович",
                false,
                "П.П. Петрович",
                false,
                "С.А. Владимирович"
        );
    }

    @Nested
    @DisplayName("Тесты на метод generatedDocument")
class GeneratedDocumentTests {

        @Test
        @Tag("E2E")
        @DisplayName("Тест с позитивным исходом")
        void generatedDocumentTest() {

            responseEntity = restTemplate.postForEntity(url, request, byte[].class);

            assertNotNull(responseEntity);
            assertTrue(responseEntity.getStatusCode().isSameCodeAs(HttpStatus.OK));
            assertNotNull(responseEntity.getBody());
            assertTrue(responseEntity.getBody().length >= 21949 && responseEntity.getBody().length <= 21963);
        }
    }
}
