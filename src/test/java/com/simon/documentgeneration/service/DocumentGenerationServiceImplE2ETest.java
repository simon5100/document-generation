package com.simon.documentgeneration.service;

import com.simon.documentgeneration.dto.DocumentJobRegulationRequest;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@Log4j2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("E2E тесты методов DocumentGenerationServiceImpl")
public class DocumentGenerationServiceImplE2ETest {

    @Autowired
    private TestRestTemplate restTemplate;

    private DocumentJobRegulationRequest request;

    private String url = "http://localhost:8081/api/documents/v1/generation/jobRegulation/secretarySession";


    @BeforeEach
    void setUp(){

        request = new DocumentJobRegulationRequest(
                "Железнодорожный",
                1,
                "И.И. Иванович",
                false,
                "П.П. Петрович",
                false,
                "С.А. Владимирович"
        );
    }


}
