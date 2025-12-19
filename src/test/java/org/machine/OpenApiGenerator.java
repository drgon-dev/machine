package org.machine;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class OpenApiGenerator {

    @Test
    public void generateOpenApiSpec() throws Exception {
        OpenAPI openAPI = context.getBean(OpenAPI.class);
        // Или отправляете запрос к /v3/api-docs

        // 2. Преобразуете её в JSON строку
        String json = new ObjectMapper().writeValueAsString(openAPI);

        // 3. Записываете в файл в build директории
        Path outputPath = Paths.get("build/openapi/openapi.json");
        Files.createDirectories(outputPath.getParent());
        // Files.writeString(outputPath, json, StandardCharsets.UTF_8);
        System.out.println("OpenAPI spec generated at: " + outputPath.toAbsolutePath());
    }
}