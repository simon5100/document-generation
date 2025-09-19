package com.simon.documentgeneration.util;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Declension convert", description = "склоняет наименование районов по падежам")
public class Declension {

    StringBuilder stringBuilder;

    @Operation(summary = "Склоняет наименование района в родительный падеж")
    public String getGenitiveCase(String word) {

        stringBuilder = new StringBuilder(word);

        stringBuilder.replace(word.length()-2, word.length(), "ого");

        return stringBuilder.toString();
    }
}